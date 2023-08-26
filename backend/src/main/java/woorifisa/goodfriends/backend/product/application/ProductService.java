package woorifisa.goodfriends.backend.product.application;

import org.springframework.data.domain.PageRequest;
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
import woorifisa.goodfriends.backend.user.domain.User;
import woorifisa.goodfriends.backend.user.domain.UserRepository;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    private final ProductImageRepository productImageRepository;

    private final S3Service s3Service;

    public ProductService(UserRepository userRepository, ProductRepository productRepository, ProductImageRepository productImageRepository, S3Service s3Service) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.productImageRepository = productImageRepository;
        this.s3Service = s3Service;
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
                .productCategories(request.getProductCategories())
                .description(request.getDescription())
                .sellPrice(request.getSellPrice())
                .build());
    }

    private String saveImage(Long productId, MultipartFile image) throws IOException {
        String uniqueFileName = FileUtils.generateUniqueFileName(Objects.requireNonNull(image.getOriginalFilename(), "파일명을 변경할 파일이 없습니다."));
        String savedImageUrl = s3Service.saveFile(image, uniqueFileName);

        productImageRepository.save(new ProductImage(productRepository.findById(productId).orElseThrow(), savedImageUrl));

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
                    List<String> images = productImageRepository.findOneImageUrlByProductId(product.getId(), PageRequest.of(0,1));
                    String image = "";

                    if(!images.isEmpty()){
                        image = images.get(0);
                    }

                    return new ProductViewAllResponse(
                            product.getId(), product.getProductCategories().getId(), product.getTitle(), product.getStatus(), product.getSellPrice(), image);
                })
                .collect(Collectors.toList());
    }

    public ProductViewOneResponse viewOneProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow();
        List<String> images = productImageRepository.findAllImageUrlByProductId(product.getId());

        return new ProductViewOneResponse(product.getId(), product.getUser(), product.getProductCategories().getId(), product.getTitle(), product.getStatus(), product.getSellPrice(), product.getCreatedDate(), product.getLastModifiedDate(), images);
    }

    public ProductUpdateResponse showSelectedProduct(Long id) {
        Product selectedProduct = productRepository.findById(id).orElseThrow();
        List<String> images = productImageRepository.findAllImageUrlByProductId(id);
        return new ProductUpdateResponse(selectedProduct, images);
    }

    @Transactional
    public ProductUpdateResponse updateProduct(ProductUpdateRequest request, Long id) throws IOException {
        Product selectedProduct = productRepository.findById(id).orElseThrow();

        deleteImageByProductId(id);
        productImageRepository.deleteByProductId(id);

        List<String> savedImageUrls = saveImages(id, request.getImageUrls());

        Product updatedProduct = productRepository.save(Product.builder()
                        .id(id)
                        .user(selectedProduct.getUser())
                        .title(request.getTitle())
                        .productCategories(request.getProductCategories())
                        .status(selectedProduct.getStatus())
                        .description(request.getDescription())
                        .sellPrice(request.getSellPrice())
                        .createdDate(selectedProduct.getCreatedDate())
                        .build());

        return new ProductUpdateResponse(updatedProduct, savedImageUrls);
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
}
