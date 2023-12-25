package woorifisa.goodfriends.backend.product.application;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import woorifisa.goodfriends.backend.global.application.S3Service;
import woorifisa.goodfriends.backend.global.config.utils.FileUtils;
import woorifisa.goodfriends.backend.offender.domain.Offender;
import woorifisa.goodfriends.backend.offender.domain.OffenderRepository;
import woorifisa.goodfriends.backend.product.domain.*;
import woorifisa.goodfriends.backend.product.dto.request.ProductCreateRequest;
import woorifisa.goodfriends.backend.product.dto.request.ProductUpdateRequest;
import woorifisa.goodfriends.backend.product.dto.response.ProductUpdateResponse;
import woorifisa.goodfriends.backend.product.dto.response.ProductResponse;
import woorifisa.goodfriends.backend.product.dto.response.ProductDetailResponse;
import woorifisa.goodfriends.backend.product.dto.response.ProductsResponse;
import woorifisa.goodfriends.backend.product.exception.NotAccessProductException;
import woorifisa.goodfriends.backend.product.exception.NotAccessThisProductException;
import woorifisa.goodfriends.backend.profile.domain.Profile;
import woorifisa.goodfriends.backend.profile.domain.ProfileRepository;
import woorifisa.goodfriends.backend.profile.exception.NotFoundProfileException;
import woorifisa.goodfriends.backend.user.domain.User;
import woorifisa.goodfriends.backend.user.domain.UserRepository;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
public class ProductService {

    private static final String PRODUCT_CATEGORY_ALL = "ALL";
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final S3Service s3Service;
    private final ProfileRepository profileRepository;
    private final OffenderRepository offenderRepository;

    public ProductService(final UserRepository userRepository, final ProductRepository productRepository,
                          final ProductImageRepository productImageRepository, final S3Service s3Service,
                          final ProfileRepository profileRepository, final OffenderRepository offenderRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.productImageRepository = productImageRepository;
        this.s3Service = s3Service;
        this.profileRepository = profileRepository;
        this.offenderRepository = offenderRepository;
    }

    @Transactional
    public Long saveProduct(final Long userId, final ProductCreateRequest request, final List<MultipartFile> multipartFiles) throws IOException {
        if(existOffender(userId)) {
            throw new NotAccessProductException();
        }
        if(!existProfile(userId)) {
            throw new NotFoundProfileException();
        }

        User foundUser = userRepository.getById(userId);
        ProductCreateRequest newRequest = createProductCreateRequest(request, multipartFiles);
        Product newProduct = productRepository.save(newRequest.toEntity(foundUser, newRequest));

        // 저장한 상품ID를 가져와서 상품 이미지 저장
        saveImages(newProduct.getId(), newRequest.getImageUrls());
        return newProduct.getId();
    }

    private static ProductCreateRequest createProductCreateRequest(final ProductCreateRequest request, final List<MultipartFile> multipartFiles) {
        return new ProductCreateRequest(
                request.getTitle(),
                request.getProductCategory(),
                request.getDescription(),
                request.getSellPrice(),
                multipartFiles);
    }

    private boolean existOffender(final Long userId) {
        Offender offender = offenderRepository.findByUserId(userId);
        return offender != null;
    }

    private boolean existProfile(final Long userId) {
        Profile profile = profileRepository.findByUserId(userId).orElse(null);
        return profile != null;
    }

    private List<String> saveImages(final Long productId, final List<MultipartFile> images) throws IOException {
        List<String> savedImages = new ArrayList<>();

        for(MultipartFile image : images)
            if(!image.isEmpty())
                savedImages.add(saveImage(productId, image));
        return savedImages;
    }

    private String saveImage(final Long productId, final MultipartFile image) throws IOException {
        String uniqueFileName = FileUtils.generateUniqueFileName(image.getOriginalFilename());
        String savedImageUrl = s3Service.saveFile(image, uniqueFileName);

        ProductImage productImage = new ProductImage(productRepository.getById(productId), savedImageUrl);
        productImageRepository.save(productImage);
        return savedImageUrl;
    }

    public ProductsResponse findSearchProduct(Pageable pageable, final String productCategory, final String keyword) {
        List<Product> products;

        if(productCategory.equals(PRODUCT_CATEGORY_ALL)){
            products = productRepository.findByTitleContains(pageable, keyword);
        }
        else {
            ProductCategory category = ProductCategory.valueOf(productCategory);
            products = productRepository.findByTitleContainsInCategory(pageable, category, keyword);
        }

        List<ProductResponse> responses = createViewList(products);

        return new ProductsResponse(responses);
    }

    public ProductsResponse findProductByCategory(Pageable pageable, ProductCategory productCategory) {
        List<Product> products = productRepository.findByProductCategory(pageable, productCategory);

        List<ProductResponse> responses = createViewList(products);

        return new ProductsResponse(responses);
    }

    public ProductsResponse findAllProducts(Pageable pageable) {
        List<Product> products = productRepository.findAllOrderByIdDesc(pageable);

        List<ProductResponse> responses = createViewList(products);

        return new ProductsResponse(responses);
    }

    private List<ProductResponse> createViewList(List<Product> products) {
        List<ProductResponse> responses = products.stream()
                .map(product -> {
                    String image = productImageRepository.findOneImageUrlByProductId(product.getId());
                    if(product.getUser() == null) {
                        ProductResponse productResponse = new ProductResponse(
                                product.getId(), product.getProductCategory(), product.getTitle(), product.getStatus(), product.getSellPrice(), image, null, true);

                        return productResponse;
                    }

                    User user = userRepository.getById(product.getUser().getId());
                    Profile profile = profileRepository.getByUserId(product.getUser().getId());

                    ProductResponse productResponse = new ProductResponse(
                            product.getId(), product.getProductCategory(), product.getTitle(), product.getStatus(), product.getSellPrice(), image, profile.getAddress(), user.isActivated());
                    return productResponse;
                })
                .filter(
                        productResponse -> productResponse.isActivated()
                )
                .collect(Collectors.toList());

        return responses;
    }

    public ProductDetailResponse findProduct(Long userId, Long productId) {

        //부정행위자로 등록된 유저는 상품 상세 페이지 들어가지 못하도록
        if(existOffender(userId)) {
            throw new NotAccessProductException();
        }

        if(!existProfile(userId)) {
            throw new NotFoundProfileException(); // 403
        }

        Product product = productRepository.getById(productId);
        System.out.println(product.getCreatedAt());
        List<String> images = productImageRepository.findAllImageUrlByProductId(product.getId());

        if(product.getUser() == null){
            ProductDetailResponse response = new ProductDetailResponse(product.getId(), null, product.getAdmin().getId(), product.getProductCategory(), product.getTitle(), product.getDescription(),
                    product.getStatus(), product.getSellPrice(), product.getCreatedAt(), product.getLastModifiedAt(), images, null, "관리자");

            return response;
        }

        User user = userRepository.getById(product.getUser().getId());
        ProductDetailResponse response = new ProductDetailResponse(product.getId(), product.getUser().getId(), null, product.getProductCategory(), product.getTitle(), product.getDescription(),
                product.getStatus(), product.getSellPrice(), product.getCreatedAt(), product.getLastModifiedAt(), images, user.getProfileImageUrl(), user.getNickname());

        return response;
    }

    public ProductUpdateResponse findEditProduct(Long userId, Long productId) {
        if(!verifyUser(userId, productId)){
            throw new NotAccessThisProductException();
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
            throw new NotAccessThisProductException();
        }

        Product selectedProduct = productRepository.getById(productId);

        deleteImageByProductId(productId);
        productImageRepository.deleteByProductId(productId);

        List<String> savedImageUrls = saveImages(productId, request.getImageUrls());

        Product updateProduct = Product.builder()
                .user(selectedProduct.getUser())
                .title(request.getTitle())
                .productCategory(request.getProductCategory())
                .status(selectedProduct.getStatus())
                .description(request.getDescription())
                .sellPrice(request.getSellPrice())
                .build();

        updateProduct.validDescription(updateProduct.getDescription());

        Product updatedProduct = productRepository.save(updateProduct);
    }

    @Transactional
    public void deleteById(Long userId, Long productId) throws MalformedURLException {
        if(!verifyUser(userId, productId)){
            throw new NotAccessThisProductException();
        }
        deleteImageByProductId(productId);
        productRepository.deleteById(productId);
    }

    private void deleteImageByProductId(Long productId) throws MalformedURLException {
        List<ProductImage> productImages = productImageRepository.findByProductId(productId);
        for (ProductImage productImage : productImages) {
            s3Service.deleteFile(productImage.getImageUrl());
        }
    }
}