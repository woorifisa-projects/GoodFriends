package woorifisa.goodfriends.backend.product.application;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import woorifisa.goodfriends.backend.global.application.S3Service;
import woorifisa.goodfriends.backend.global.config.utils.FileUtils;
import woorifisa.goodfriends.backend.product.domain.*;
import woorifisa.goodfriends.backend.product.dto.request.ProductSaveRequest;
import woorifisa.goodfriends.backend.product.dto.request.ProductUpdateRequest;
import woorifisa.goodfriends.backend.product.dto.response.ProductSaveResponse;
import woorifisa.goodfriends.backend.product.dto.response.ProductUpdateResponse;
import woorifisa.goodfriends.backend.product.dto.response.ProductViewAllResponse;
import woorifisa.goodfriends.backend.product.dto.response.ProductViewOneResponse;
import woorifisa.goodfriends.backend.profile.domain.Profile;
import woorifisa.goodfriends.backend.profile.domain.ProfileRepository;
import woorifisa.goodfriends.backend.user.domain.User;
import woorifisa.goodfriends.backend.user.domain.UserRepository;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    private final ProductImageRepository productImageRepository;

    private final S3Service s3Service;

    private final ProfileRepository profileRepository;

    public ProductService(UserRepository userRepository, ProductRepository productRepository, ProductImageRepository productImageRepository, S3Service s3Service, ProfileRepository profileRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.productImageRepository = productImageRepository;
        this.s3Service = s3Service;
        this.profileRepository = profileRepository;
    }

    public ProductSaveResponse saveProduct(Long userId, ProductSaveRequest request) throws IOException {
        User foundUser = userRepository.getById(userId);

        Product newProduct = createProduct(foundUser, request);

        List<String> savedImageUrls = saveImages(newProduct.getId(), request.getImageUrls());

        return new ProductSaveResponse(newProduct, savedImageUrls);
    }

    private Product createProduct(User user, ProductSaveRequest request) {
        return productRepository.save(Product.builder()
                .user(user)
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
                    if(product.getUser() == null) {
                        return new ProductViewAllResponse(
                                product.getId(), product.getProductCategory(), product.getTitle(), product.getStatus(), product.getSellPrice(), image, null);
                    }

                    Profile profile = profileRepository.findByUserId(product.getUser().getId()).orElseThrow(()-> new RuntimeException("유저의 프로필이 존재하지 않습니다."));

                    return new ProductViewAllResponse(
                            product.getId(), product.getProductCategory(), product.getTitle(), product.getStatus(), product.getSellPrice(), image, profile.getAddress());
                })
                .collect(Collectors.toList());
    }

    public ProductViewOneResponse viewOneProduct(Long id) {
        Product product = productRepository.getById(id);
        List<String> images = productImageRepository.findAllImageUrlByProductId(product.getId());

        if(product.getUser() == null){
            return new ProductViewOneResponse(product.getId(), null, product.getAdmin().getId(), product.getProductCategory(), product.getTitle(),
                    product.getStatus(), product.getSellPrice(), product.getCreatedAt(), product.getLastModifiedAt(), images, null, "관리자");
        }

        User user = userRepository.getById(product.getUser().getId());
        return new ProductViewOneResponse(product.getId(), product.getUser().getId(), null, product.getProductCategory(), product.getTitle(),
                product.getStatus(), product.getSellPrice(), product.getCreatedAt(), product.getLastModifiedAt(), images, user.getProfileImageUrl(), user.getNickname());
    }

    public ProductUpdateResponse showSelectedProduct(Long id) {
        Product selectedProduct = productRepository.getById(id);
        List<String> images = productImageRepository.findAllImageUrlByProductId(id);
        return new ProductUpdateResponse(selectedProduct, images);
    }

    @Transactional
    public void updateProduct(ProductUpdateRequest request, Long id) throws IOException {
        Product selectedProduct = productRepository.getById(id);

        deleteImageByProductId(id);
        productImageRepository.deleteByProductId(id);

        List<String> savedImageUrls = saveImages(id, request.getImageUrls());

        Product updatedProduct = productRepository.save(Product.builder()
                .id(id)
                .user(selectedProduct.getUser())
                .title(request.getTitle())
                .productCategory(request.getProductCategory())
                .status(selectedProduct.getStatus())
                .description(request.getDescription())
                .sellPrice(request.getSellPrice())
                .createdAt(selectedProduct.getCreatedAt())
                .build());
    }

    public void deleteImageByProductId(Long productId) throws MalformedURLException {
        List<ProductImage> productImages = productImageRepository.findByProductId(productId);
        for(ProductImage productImage : productImages){
            s3Service.deleteFile(productImage.getImageUrl());
        }
    }
    public void deleteById(Long productId) throws MalformedURLException {
        deleteImageByProductId(productId);
        productRepository.deleteById(productId);
    }

    public boolean verifyUser(Long userId, Long productId) {
        Product product = productRepository.getById(productId);
        return product.getUser().getId() == userId;
    }

    public boolean existProfile(Long userId) {
        Profile profile = profileRepository.findByUserId(userId).orElse(null);
        return profile != null;
    }
}