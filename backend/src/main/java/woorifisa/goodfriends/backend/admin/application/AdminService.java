package woorifisa.goodfriends.backend.admin.application;


import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import woorifisa.goodfriends.backend.admin.domain.Admin;
import woorifisa.goodfriends.backend.admin.domain.AdminRepository;
import woorifisa.goodfriends.backend.admin.dto.request.UserUpdateRequest;
import woorifisa.goodfriends.backend.admin.dto.response.UserLogRecordResponse;
import woorifisa.goodfriends.backend.admin.dto.response.UserLogRecordsResponse;
import woorifisa.goodfriends.backend.admin.exception.InvalidAdminException;
import woorifisa.goodfriends.backend.auth.application.TokenCreator;
import woorifisa.goodfriends.backend.auth.domain.AuthToken;
import woorifisa.goodfriends.backend.auth.dto.response.AccessTokenResponse;
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
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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
    public AdminService(AdminRepository adminRepository,UserRepository userRepository, ProductRepository productRepository,
                        ProductImageRepository productImageRepository, S3Service s3Service,
                        ProfileRepository profileRepository,TokenCreator tokenCreator) {
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.productImageRepository = productImageRepository;
        this.s3Service = s3Service;
        this.profileRepository = profileRepository;
        this.tokenCreator = tokenCreator;
    }

    public AccessTokenResponse login(String root, String password){
        // adminId가 틀린 경우
        Admin selectedAdmin = adminRepository.findByRoot(root)
                .orElseThrow(() -> new InvalidAdminException( root + "와 일치하는 아이디가 없습니다."));

        // password가 틀린 경우
        if(!selectedAdmin.getPassword().equals(password)) {
            throw new InvalidAdminException("잘못된 비밀번호입니다.");
        }

        // 앞에서 Exception 안났으면 토큰 발행 구현해야함
        AuthToken authToken = tokenCreator.createAdminToken(selectedAdmin.getId());

        return new AccessTokenResponse(authToken.getId(), authToken.getAccessToken());
    }

    public ProductSaveResponse saveProduct(long adminId, ProductSaveRequest request) throws IOException {
        Admin foundAdmin = adminRepository.findById(adminId).orElseThrow(() -> new InvalidAdminException(adminId + "와 일치하는 아이디가 없습니다."));

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
                    if(product.getUser() == null) {
                        ProductViewAllResponse productViewAllResponse = new ProductViewAllResponse(
                                product.getId(), product.getProductCategory(), product.getTitle(), product.getStatus(), product.getSellPrice(), image, null);

                        return productViewAllResponse;
                    }

                    Profile profile = profileRepository.findByUserId(product.getUser().getId()).orElseThrow(()-> new RuntimeException("유저의 프로필이 없습니다."));

                    ProductViewAllResponse productViewAllResponse = new ProductViewAllResponse(
                            product.getId(), product.getProductCategory(), product.getTitle(), product.getStatus(), product.getSellPrice(), image, profile.getAddress());
                    return productViewAllResponse;
                })
                .collect(Collectors.toList());
    }

    public ProductViewOneResponse viewOneProduct(Long id) {
        Product product = productRepository.getById(id);
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

    public ProductUpdateResponse showSelectedProduct(Long id) {
        Product selectedProduct = productRepository.getById(id);
        List<String> images = productImageRepository.findAllImageUrlByProductId(id);
        return new ProductUpdateResponse(selectedProduct, images);
    }

    @Transactional
    public ProductUpdateResponse updateProduct(ProductUpdateRequest request, Long id) throws IOException {
        Product selectedProduct = productRepository.getById(id);

        deleteImageByProductId(id);
        productImageRepository.deleteByProductId(id);

        List<String> savedImageUrls = saveImages(id, request.getImageUrls());

        Product updatedProduct = productRepository.save(Product.builder()
                .id(id)
                .user(selectedProduct.getUser())
                .admin(selectedProduct.getAdmin())
                .title(request.getTitle())
                .productCategory(request.getProductCategory())
                .status(selectedProduct.getStatus())
                .description(request.getDescription())
                .sellPrice(request.getSellPrice())
                .createdAt(selectedProduct.getCreatedAt())
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

    //  관리자가 사용자 정보 가져오기
    public UserLogRecordsResponse findUserLogRecord() {
        List<UserLogRecordResponse> userResponses = userRepository.findAll()
                .stream()
                .map(UserLogRecordResponse::new)
                .sorted(Comparator.comparing(UserLogRecordResponse::getLastModifiedAt).reversed())
                .collect(Collectors.toList());
        return new UserLogRecordsResponse(userResponses);
    }

    // 관리자가 사용자 정보 삭제
    public void deleteUserInfo(Long userId){
         userRepository.deleteById(userId);
    }

    // 관리자가 사용자 정보 수정
    public void updateUserInfo(Long userId, UserUpdateRequest request){
        User user = userRepository.getById(userId);
        userRepository.save(User.builder()
                        .id(userId)
                        .email(user.getEmail())
                        .nickname(request.getNickname())
                        .profileImageUrl(user.getProfileImageUrl())
                        .ban(request.getBanCount())
//  비활성화              .activated(request.getActivated())
                        .createdAt(user.getCreatedAt())
                        .build());

    }

}
