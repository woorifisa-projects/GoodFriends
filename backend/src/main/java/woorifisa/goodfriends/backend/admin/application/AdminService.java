package woorifisa.goodfriends.backend.admin.application;


import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import woorifisa.goodfriends.backend.admin.domain.Admin;
import woorifisa.goodfriends.backend.admin.domain.AdminRepository;
import woorifisa.goodfriends.backend.admin.dto.request.UserUpdateRequest;
import woorifisa.goodfriends.backend.admin.dto.response.UserInfoResponse;
import woorifisa.goodfriends.backend.admin.dto.response.UserLogRecordResponse;
import woorifisa.goodfriends.backend.admin.dto.response.UserLogRecordsResponse;
import woorifisa.goodfriends.backend.admin.exception.NotFoundAdminException;
import woorifisa.goodfriends.backend.auth.application.TokenCreator;
import woorifisa.goodfriends.backend.auth.domain.AuthToken;
import woorifisa.goodfriends.backend.auth.dto.response.AccessTokenResponse;
import woorifisa.goodfriends.backend.global.application.S3Service;
import woorifisa.goodfriends.backend.global.config.utils.FileUtils;
import woorifisa.goodfriends.backend.product.domain.*;
import woorifisa.goodfriends.backend.product.dto.request.ProductSaveRequest;
import woorifisa.goodfriends.backend.product.dto.request.ProductUpdateRequest;
import woorifisa.goodfriends.backend.product.dto.response.ProductUpdateResponse;
import woorifisa.goodfriends.backend.product.dto.response.ProductViewAllResponse;
import woorifisa.goodfriends.backend.product.dto.response.ProductViewOneResponse;
import woorifisa.goodfriends.backend.product.dto.response.ProductViewsAllResponse;
import woorifisa.goodfriends.backend.profile.domain.Profile;
import woorifisa.goodfriends.backend.profile.domain.ProfileRepository;
import woorifisa.goodfriends.backend.report.domain.ReportRepository;
import woorifisa.goodfriends.backend.report.dto.response.ReportResponse;
import woorifisa.goodfriends.backend.report.dto.response.ReportsResponse;
import woorifisa.goodfriends.backend.user.domain.User;
import woorifisa.goodfriends.backend.user.domain.UserRepository;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.MalformedURLException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import java.util.stream.Collectors;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final S3Service s3Service;
    private final ProfileRepository profileRepository;
    private final TokenCreator tokenCreator;
    private final ReportRepository reportRepository;

    public AdminService(AdminRepository adminRepository, UserRepository userRepository,
                        ProductRepository productRepository, ProductImageRepository productImageRepository,
                        S3Service s3Service, ProfileRepository profileRepository, TokenCreator tokenCreator,
                        ReportRepository reportRepository) {
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.productImageRepository = productImageRepository;
        this.s3Service = s3Service;
        this.profileRepository = profileRepository;
        this.tokenCreator = tokenCreator;
        this.reportRepository = reportRepository;
    }

    public AccessTokenResponse login(String root, String password) {
        // adminId가 틀린 경우

        Admin selectedAdmin = adminRepository.getByRoot(root);

        // password가 틀린 경우
        if(!selectedAdmin.getPassword().equals(password)) {
            throw new NotFoundAdminException();
        }

        // 앞에서 Exception 안났으면 토큰 발행 구현해야함
        AuthToken authToken = tokenCreator.createAdminToken(selectedAdmin.getId());

        return new AccessTokenResponse(authToken.getId(), authToken.getAccessToken());
    }

    // 사용자 로그기록 전체 조회
    public UserLogRecordsResponse findUserLogRecord() {
        List<UserLogRecordResponse> userResponses = userRepository.findAll()
                .stream()
                .map(UserLogRecordResponse::new)
                .sorted(Comparator.comparing(UserLogRecordResponse::getLastModifiedAt).reversed())
                .collect(Collectors.toList());
        return new UserLogRecordsResponse(userResponses);
    }

    // 사용자 정보 전체 조회
    public List<UserInfoResponse> getAllUsers() {
        List<Object[]> results = userRepository.getAllUserInfo();
        List<UserInfoResponse> userInfoList = results.stream()
                .map(result -> {
                    User user = (User) result[0];
                    Profile profile = (Profile) result[1];

                    return new UserInfoResponse(
                            user.getId(),user.getEmail(), user.getNickname(), user.getProfileImageUrl(), user.getCreatedAt(), user.getLastModifiedAt()
                            , user.getBan(), profile.getMobileNumber(), profile.getAddress(), user.isActivated(),profile.getAccountType(),profile.getAccountNumber()
                    );
                }).collect(Collectors.toList());

        return userInfoList;
    }

    // 사용자 정보 수정
    public void updateUserInfo(Long userId, UserUpdateRequest request) {
        User user = userRepository.getById(userId);
        Profile profile = profileRepository.getByUserId(userId);
        userRepository.save(User.builder()
                .id(userId)
                .email(user.getEmail())
                .nickname(request.getNickname())
                .profileImageUrl(user.getProfileImageUrl())
                .ban(request.getBanCount())
                .activated(request.isActivated())
                .build());
        profileRepository.save(Profile.builder()
                .id(profile.getId())
                .user(user)
                .mobileNumber(request.getMobilePhone())
                .address(request.getAddress())
                .build());

    }

    // 사용자 정보 삭제
    public void deleteUserInfo(Long userId) {
        userRepository.deleteById(userId);
    }

    // 상품 등록
    public Long saveProduct(long adminId, ProductSaveRequest request) throws IOException {
        Admin foundAdmin = adminRepository.getById(adminId);

        // 상품 저장
        Product newProduct = productRepository.save(createProduct(foundAdmin, request));

        // 저장한 상품 id를 가져와서 상품 이미지 저장
        saveImages(newProduct.getId(), request.getImageUrls());

        return newProduct.getId();
    }

    private Product createProduct(Admin admin, ProductSaveRequest request) {
        Product newProduct = Product.builder()
                .admin(admin)
                .title(request.getTitle())
                .status(ProductStatus.SELL)
                .productCategory(request.getProductCategory())
                .description(request.getDescription())
                .sellPrice(request.getSellPrice())
                .build();
        newProduct.validDescription(newProduct.getDescription());
        return newProduct;
    }

    private String saveImage(Long productId, MultipartFile image) throws IOException {
        String uniqueFileName = FileUtils.generateUniqueFileName(image.getOriginalFilename());
        String savedImageUrl = s3Service.saveFile(image, uniqueFileName);

        productImageRepository.save(new ProductImage(productRepository.getById(productId), savedImageUrl));

        return savedImageUrl;
    }

    private List<String> saveImages(Long productId, List<MultipartFile> images) throws IOException {
        List<String> savedImages = new ArrayList<>();
        for (MultipartFile image : images) {
            if (!image.isEmpty()) {
                savedImages.add(saveImage(productId, image));
            }
        }
        return savedImages;
    }

    // 상품 검색
    public ProductViewsAllResponse viewSearchProduct(String keyword) {
        List<Product> products = productRepository.findByTitleContains(Pageable.unpaged(), keyword);

        List<ProductViewAllResponse> responses = createViewList(products);

        return new ProductViewsAllResponse(responses);
    }

    // 상품 전체 조회
    public ProductViewsAllResponse viewAllProduct() {
        List<Product> products = productRepository.findAllOrderByIdDesc(Pageable.unpaged());

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

    // 상품 상세 조회
    public ProductViewOneResponse viewOneProduct(Long id) {
        Product product = productRepository.getById(id);
        List<String> images = productImageRepository.findAllImageUrlByProductId(product.getId());

        if (product.getUser() == null) {
            ProductViewOneResponse response = new ProductViewOneResponse(product.getId(), null, product.getAdmin().getId(), product.getProductCategory(), product.getTitle(), product.getDescription(),
                    product.getStatus(), product.getSellPrice(), product.getCreatedAt(), product.getLastModifiedAt(), images, null, "관리자");

            return response;
        }

        User user = userRepository.getById(product.getUser().getId());
        ProductViewOneResponse response = new ProductViewOneResponse(product.getId(), product.getUser().getId(), null, product.getProductCategory(), product.getTitle(), product.getDescription(),
                product.getStatus(), product.getSellPrice(), product.getCreatedAt(), product.getLastModifiedAt(), images, user.getProfileImageUrl(), user.getNickname());
        return response;
    }

    // 수정할 상품
    public ProductUpdateResponse showSelectedProduct(Long id) {
        Product selectedProduct = productRepository.getById(id);
        List<String> images = productImageRepository.findAllImageUrlByProductId(id);
        return new ProductUpdateResponse(selectedProduct, images);
    }

    // 상품 수정
    @Transactional
    public ProductUpdateResponse updateProduct(ProductUpdateRequest request, Long id) throws IOException {
        Product selectedProduct = productRepository.getById(id);

        deleteImageByProductId(id);
        productImageRepository.deleteByProductId(id);

        List<String> savedImageUrls = saveImages(id, request.getImageUrls());

        Product updateProduct = Product.builder()
                .id(id)
                .user(selectedProduct.getUser())
                .admin(selectedProduct.getAdmin())
                .title(request.getTitle())
                .productCategory(request.getProductCategory())
                .status(selectedProduct.getStatus())
                .description(request.getDescription())
                .sellPrice(request.getSellPrice())
                .createdAt(selectedProduct.getCreatedAt())
                .build();

        updateProduct.validDescription(updateProduct.getDescription());

        Product updatedProduct = productRepository.save(updateProduct);

        return new ProductUpdateResponse(updatedProduct, savedImageUrls);
    }

    // 상품 삭제
    public void deleteById(Long productId) throws MalformedURLException {
        deleteImageByProductId(productId);
        productRepository.deleteById(productId);
    }

    public void deleteImageByProductId(Long productId) throws MalformedURLException {
        List<ProductImage> productImages = productImageRepository.findByProductId(productId);
        for (ProductImage productImage : productImages) {
            s3Service.deleteFile(productImage.getImageUrl());
        }
    }

    // 상품 신고 전체 조회
    public ReportsResponse viewAllProductsReport() {
        List<ReportResponse> declarationResponses = reportRepository.findAllReportByIdDesc()
                .stream()
                .map(ReportResponse::new)
                .collect(Collectors.toList());
        return new ReportsResponse(declarationResponses);
    }
}