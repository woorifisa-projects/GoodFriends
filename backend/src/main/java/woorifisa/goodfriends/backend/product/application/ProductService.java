package woorifisa.goodfriends.backend.product.application;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import woorifisa.goodfriends.backend.global.application.S3Service;
import woorifisa.goodfriends.backend.global.config.utils.FileUtils;
import woorifisa.goodfriends.backend.product.domain.*;
import woorifisa.goodfriends.backend.product.dto.request.ProductSaveRequest;
import woorifisa.goodfriends.backend.product.dto.request.ProductUpdateRequest;
import woorifisa.goodfriends.backend.product.dto.response.ProductUpdateResponse;
import woorifisa.goodfriends.backend.product.dto.response.ProductViewAllResponse;
import woorifisa.goodfriends.backend.product.dto.response.ProductViewOneResponse;
import woorifisa.goodfriends.backend.product.dto.response.ProductViewsAllResponse;
import woorifisa.goodfriends.backend.product.exception.NotAccessThisProduct;
import woorifisa.goodfriends.backend.profile.domain.Profile;
import woorifisa.goodfriends.backend.profile.domain.ProfileRepository;
import woorifisa.goodfriends.backend.profile.exception.NotFoundProfile;
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

    public Long saveProduct(Long userId, ProductSaveRequest request) throws IOException {

        //프로필 등록해야 상품 등록 가능하도록
        if(!existProfile(userId)) {
            throw new NotFoundProfile(); // 403
        }

        User foundUser = userRepository.getById(userId);
        // 상품 저장
        Product newProduct = productRepository.save(createProduct(foundUser, request));

        // 저장한 상품 id를 가져와서 상품 이미지 저장
        saveImages(newProduct.getId(), request.getImageUrls());

        return newProduct.getId();
    }

    public boolean existProfile(Long userId) {
        Profile profile = profileRepository.findByUserId(userId).orElse(null);
        return profile != null;
    }

    private Product createProduct(User user, ProductSaveRequest request) {
        return Product.builder()
                .user(user)
                .title(request.getTitle())
                .status(ProductStatus.SELL)
                .productCategory(request.getProductCategory())
                .description(request.getDescription())
                .sellPrice(request.getSellPrice())
                .build();
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

    private String saveImage(Long productId, MultipartFile image) throws IOException {
        String uniqueFileName = FileUtils.generateUniqueFileName(image.getOriginalFilename());
        String savedImageUrl = s3Service.saveFile(image, uniqueFileName);

        productImageRepository.save(new ProductImage(productRepository.getById(productId), savedImageUrl));

        return savedImageUrl;
    }

    public ProductViewsAllResponse viewSearchProduct(String productCategory, String keyword) {
        List<Product> products;

        if(productCategory.equals("ALL")){
            products = productRepository.findByTitleContains(keyword);
        }
        else {
            ProductCategory category = ProductCategory.valueOf(productCategory);
            products = productRepository.findByTitleContainsInCategory(category, keyword);
        }

        List<ProductViewAllResponse> responses = createViewList(products);

        return new ProductViewsAllResponse(responses);
    }

    public ProductViewsAllResponse viewProductByCategory(Pageable pageable, ProductCategory productCategory) {
        List<Product> products = productRepository.findByProductCategory(pageable, productCategory);

        List<ProductViewAllResponse> responses = createViewList(products);

        return new ProductViewsAllResponse(responses);
    }

    public ProductViewsAllResponse viewAllProduct(Pageable pageable) {
        List<Product> products = productRepository.findAllOrderByIdDesc(pageable);

        List<ProductViewAllResponse> responses = createViewList(products);

        return new ProductViewsAllResponse(responses);
    }

    private List<ProductViewAllResponse> createViewList(List<Product> products) {
        return products.stream()
                .map(product -> {
                    String image = productImageRepository.findOneImageUrlByProductId(product.getId());
                    if(product.getUser() == null) {
                        ProductViewAllResponse productViewAllResponse = new ProductViewAllResponse(
                                product.getId(), product.getProductCategory(), product.getTitle(), product.getStatus(), product.getSellPrice(), image, null);

                        return productViewAllResponse;
                    }

                    Profile profile = profileRepository.getByUserId(product.getUser().getId());

                    ProductViewAllResponse productViewAllResponse = new ProductViewAllResponse(
                            product.getId(), product.getProductCategory(), product.getTitle(), product.getStatus(), product.getSellPrice(), image, profile.getAddress());
                    return productViewAllResponse;
                })
                .collect(Collectors.toList());
    }

    public ProductViewOneResponse viewOneProduct(Long userId, Long productId) {

        if(!existProfile(userId)) {
            throw new NotFoundProfile(); // 403
        }

        Product product = productRepository.getById(productId);
        System.out.println(product.getCreatedAt());
        List<String> images = productImageRepository.findAllImageUrlByProductId(product.getId());

        if(product.getUser() == null){
            ProductViewOneResponse response = new ProductViewOneResponse(product.getId(), null, product.getAdmin().getId(), product.getProductCategory(), product.getTitle(), product.getDescription(),
                    product.getStatus(), product.getSellPrice(), product.getCreatedAt(), product.getLastModifiedAt(), images, null, "관리자");

            return response;
        }

        User user = userRepository.getById(product.getUser().getId());
        ProductViewOneResponse response = new ProductViewOneResponse(product.getId(), product.getUser().getId(), null, product.getProductCategory(), product.getTitle(), product.getDescription(),
                product.getStatus(), product.getSellPrice(), product.getCreatedAt(), product.getLastModifiedAt(), images, user.getProfileImageUrl(), user.getNickname());

        return response;
    }

    public ProductUpdateResponse showSelectedProduct(Long userId, Long productId) {
        if(!verifyUser(userId, productId)){
            throw new NotAccessThisProduct();
        }

        Product selectedProduct = productRepository.getById(productId);
        List<String> images = productImageRepository.findAllImageUrlByProductId(productId);
        return new ProductUpdateResponse(selectedProduct, images);
    }

    public boolean verifyUser(Long userId, Long productId) {
        Product product = productRepository.getById(productId);
        return product.getUser().getId() == userId;
    }

    @Transactional
    public void updateProduct(ProductUpdateRequest request, Long userId, Long productId) throws IOException {
        if(!verifyUser(userId, productId)){
            throw new NotAccessThisProduct();
        }

        Product selectedProduct = productRepository.getById(productId);

        deleteImageByProductId(productId);
        productImageRepository.deleteByProductId(productId);

        List<String> savedImageUrls = saveImages(productId, request.getImageUrls());

        Product updatedProduct = productRepository.save(Product.builder()
                .id(productId)
                .user(selectedProduct.getUser())
                .title(request.getTitle())
                .productCategory(request.getProductCategory())
                .status(selectedProduct.getStatus())
                .description(request.getDescription())
                .sellPrice(request.getSellPrice())
                .createdAt(selectedProduct.getCreatedAt())
                .build());
    }

    public void deleteById(Long userId, Long productId) throws MalformedURLException {
        if(!verifyUser(userId, productId)){
            throw new NotAccessThisProduct();
        }
        deleteImageByProductId(productId);
        productRepository.deleteById(productId);
    }

    public void deleteImageByProductId(Long productId) throws MalformedURLException {
        List<ProductImage> productImages = productImageRepository.findByProductId(productId);
        for (ProductImage productImage : productImages) {
            s3Service.deleteFile(productImage.getImageUrl());
        }
    }
}