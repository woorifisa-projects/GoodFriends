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
import woorifisa.goodfriends.backend.product.exception.NotFoundProductException;
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
        validateUser(userId);

        User foundUser = userRepository.getByUserId(userId);
        ProductCreateRequest newRequest = createProductCreateRequest(request, multipartFiles);
        Product newProduct = productRepository.save(newRequest.toEntity(foundUser, newRequest));

        // 저장한 상품ID를 가져와서 상품 이미지 저장
        saveImages(newProduct.getId(), newRequest.getImageUrls());
        return newProduct.getId();
    }

    private void validateUser(final Long userId) {
        if(existOffender(userId)) {
            throw new NotAccessProductException();
        }
        if(!existProfile(userId)) {
            throw new NotFoundProfileException();
        }
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

        ProductImage productImage = new ProductImage(productRepository.getByProductId(productId), savedImageUrl);
        productImageRepository.save(productImage);
        return savedImageUrl;
    }

    public ProductsResponse findSearchProduct(Pageable pageable, final String productCategory, final String keyword) {
        List<Product> products;

        if(productCategory.equals(PRODUCT_CATEGORY_ALL)){
            products = productRepository.findByTitleContains(pageable, keyword);
        } else {
            ProductCategory category = ProductCategory.valueOf(productCategory);
            products = productRepository.findByTitleContainsInCategory(pageable, category, keyword);
        }
        List<ProductResponse> responses = getProducts(products);
        return new ProductsResponse(responses);
    }

    public ProductsResponse findProductByCategory(Pageable pageable, ProductCategory productCategory) {
        List<Product> products = productRepository.findByProductCategory(pageable, productCategory);
        List<ProductResponse> responses = getProducts(products);
        return new ProductsResponse(responses);
    }

    public ProductsResponse findAllProducts(Pageable pageable) {
        List<Product> products = productRepository.findAllOrderByIdDesc(pageable);
        List<ProductResponse> responses = getProducts(products);
        return new ProductsResponse(responses);
    }

    //  특정 상품 목록을 받아와서 해당 상품들에 대한 응답 객체를 생성하고, 활성화된 상품들만을 리스트로 반환하는 기능
    private List<ProductResponse> getProducts(List<Product> products) {
        return products.stream()
                .map(product -> {
                    String imageUrl = productImageRepository.findOneImageUrlByProductId(product.getId());
                    if (product.getUser() == null)
                        return ProductResponse.of(product, imageUrl);

                    User user = userRepository.getByUserId(product.getUser().getId());
                    Profile profile = profileRepository.getByUserId(product.getUser().getId());
                    return ProductResponse.of(product, imageUrl, user, profile);
                })
                .filter(ProductResponse::isActivated)
                .collect(Collectors.toList());
    }

    public ProductDetailResponse findProduct(final Long userId, final Long productId) {
        validateUser(userId);
        Product product = productRepository.getByProductId(productId);
        List<String> imageUrls = productImageRepository.findAllImageUrlByProductId(product.getId());

        if(product.getUser() == null) {
            return ProductDetailResponse.of(product, imageUrls);
        }
        User user = userRepository.getByUserId(product.getUser().getId());
        return ProductDetailResponse.of(product, imageUrls, user);
    }

    public ProductUpdateResponse findEditProduct(final Long userId, final Long productId) {
        if(!checkUserIdAndProductIdEquals(userId, productId)){
            throw new NotAccessThisProductException();
        }

        Product selectedProduct = productRepository.getByProductId(productId);
        List<String> images = productImageRepository.findAllImageUrlByProductId(productId);
        return ProductUpdateResponse.of(selectedProduct, images);
    }

    public boolean checkUserIdAndProductIdEquals(final Long userId, final Long productId) {
        if(userId == null) {
            return false;
        }
        Product product = productRepository.getByProductId(productId);
        return userId.equals(product.getUser().getId());
    }

    @Transactional
    public void updateProduct(final ProductUpdateRequest productUpdateRequest, final Long userId, final Long productId) throws IOException {
        if(!checkUserIdAndProductIdEquals(userId, productId)){
            throw new NotAccessThisProductException();
        }

        deleteImageByProductId(productId);
        productImageRepository.deleteByProductId(productId);

        Product product = findProductObject(productId);
        product.updateUser(product.getUser());
        product.updateStatus(product.getStatus());
        product.updateTitle(productUpdateRequest.getTitle());
        product.updateProductCategory(productUpdateRequest.getProductCategory());
        product.updateDescription(productUpdateRequest.getDescription());
        product.updateSellPrice(productUpdateRequest.getSellPrice());
        saveImages(productId, productUpdateRequest.getImageUrls());
    }

    private Product findProductObject(final Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(NotFoundProductException::new);
    }

    @Transactional
    public void deleteById(Long userId, Long productId) throws MalformedURLException {
        if(!checkUserIdAndProductIdEquals(userId, productId)){
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