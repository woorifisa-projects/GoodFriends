package woorifisa.goodfriends.backend.admin.application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import woorifisa.goodfriends.backend.admin.domain.Admin;
import woorifisa.goodfriends.backend.admin.domain.AdminRepository;
import woorifisa.goodfriends.backend.admin.exception.InvalidAdminException;
import woorifisa.goodfriends.backend.global.application.S3Service;
import woorifisa.goodfriends.backend.global.config.utils.FileUtils;
import woorifisa.goodfriends.backend.global.config.utils.JwtTokenProvider;
import woorifisa.goodfriends.backend.product.domain.*;
import woorifisa.goodfriends.backend.product.dto.request.ProductSaveRequest;
import woorifisa.goodfriends.backend.product.dto.response.ProductSaveResponse;
import woorifisa.goodfriends.backend.product.dto.response.ProductViewAllResponse;
import woorifisa.goodfriends.backend.product.dto.response.ProductViewOneResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    private final ProductRepository productRepository;

    private final ProductImageRepository productImageRepository;

    private final S3Service s3Service;

    @Value("${security.jwt.token.secret-key}") //lombok 아닌 springframework annotation
    private String secretKey;

    @Value("${security.jwt.token.access.expire-length}")
    private Long expireTimeMs;

    public AdminService(AdminRepository adminRepository, ProductRepository productRepository, ProductImageRepository productImageRepository, S3Service s3Service) {
        this.adminRepository = adminRepository;
        this.productRepository = productRepository;
        this.productImageRepository = productImageRepository;
        this.s3Service = s3Service;
    }

    public String login(String root, String password){
        // adminId가 틀린 경우
        Admin selectedAdmin = adminRepository.findByRoot(root)
                .orElseThrow(() -> new InvalidAdminException(root + "와 일치하는 아이디가 없습니다."));

        // password가 틀린 경우
        if(!selectedAdmin.getPassword().equals(password)) {
            throw new InvalidAdminException("잘못된 비밀번호입니다.");
        }

        // 앞에서 Exception 안났으면 토큰 발행 구현해야함
        String token = JwtTokenProvider.createToken(selectedAdmin.getRoot(), secretKey, expireTimeMs);

        return token;
    }

    public ProductSaveResponse saveProduct(String root, ProductSaveRequest request) throws IOException {
        Admin foundAdmin = adminRepository.findByRoot(root).orElseThrow(() -> new InvalidAdminException(root + "와 일치하는 아이디가 없습니다."));

        Product newProduct = createProduct(foundAdmin, request);

        List<String> savedImageUrls = saveImages(newProduct.getId(), request.getImageUrls());

        return new ProductSaveResponse(newProduct, savedImageUrls);
    }

    private Product createProduct(Admin admin, ProductSaveRequest request) {
        return productRepository.save(Product.builder()
                .admin(admin)
                .title(request.getTitle())
                .status(ProductStatus.SELL)
                .productCategory(request.getProductCategory())
                .description(request.getDescription())
                .sellPrice(request.getSellPrice())
                .build());
    }

    private String saveImage(Long productId, MultipartFile image) throws IOException {
        String uniqueFileName = FileUtils.generateUniqueFileName(image.getOriginalFilename());
        String savedImageUrl = s3Service.saveFile(image, uniqueFileName);

        productImageRepository.save(new ProductImage(productRepository.getById(productId), savedImageUrl));

        return savedImageUrl;
    }

    private List<String> saveImages(Long productId, List<MultipartFile> images) throws IOException {
        List<String> savedImages = new ArrayList<>();
        for(MultipartFile image : images) {
            if(!image.isEmpty()) {
                savedImages.add(saveImage(productId, image));
            }
        }
        return savedImages;
    }

    public List<ProductViewAllResponse> viewAllProduct() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> {
                    String image = productImageRepository.findOneImageUrlByProductId(product.getId());

                    return new ProductViewAllResponse(
                            product.getId(), product.getProductCategory(), product.getTitle(), product.getStatus(), product.getSellPrice(), image);
                })
                .collect(Collectors.toList());
    }

    public ProductViewOneResponse viewOneProduct(Long id) {
        Product product = productRepository.getById(id);
        List<String> images = productImageRepository.findAllImageUrlByProductId(product.getId());

        if(product.getUser() == null){
            return new ProductViewOneResponse(product.getId(), null, product.getAdmin().getId(), product.getProductCategory(), product.getTitle(), product.getStatus(), product.getSellPrice(), product.getCreatedAt(), product.getLastModifiedAt(), images);
        }

        return new ProductViewOneResponse(product.getId(), product.getUser().getId(), null, product.getProductCategory(), product.getTitle(), product.getStatus(), product.getSellPrice(), product.getCreatedAt(), product.getLastModifiedAt(), images);
    }

}
