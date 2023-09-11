package woorifisa.goodfriends.backend.profile.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import woorifisa.goodfriends.backend.offender.domain.Offender;
import woorifisa.goodfriends.backend.offender.domain.OffenderRepository;
import woorifisa.goodfriends.backend.order.domain.ConfirmStatus;
import woorifisa.goodfriends.backend.order.domain.Order;
import woorifisa.goodfriends.backend.order.domain.OrderRepository;
import woorifisa.goodfriends.backend.product.domain.Product;
import woorifisa.goodfriends.backend.product.domain.ProductImageRepository;
import woorifisa.goodfriends.backend.product.domain.ProductRepository;
import woorifisa.goodfriends.backend.product.domain.ProductStatus;
import woorifisa.goodfriends.backend.product.exception.NotAccessProduct;
import woorifisa.goodfriends.backend.profile.dto.response.*;
import woorifisa.goodfriends.backend.profile.domain.Profile;
import woorifisa.goodfriends.backend.profile.domain.ProfileRepository;
import woorifisa.goodfriends.backend.profile.dto.request.ProfileUpdateRequest;
import woorifisa.goodfriends.backend.profile.dto.response.ProfileViewResponse;
import woorifisa.goodfriends.backend.profile.exception.AlreadyExitPhoneProfile;
import woorifisa.goodfriends.backend.user.domain.User;
import woorifisa.goodfriends.backend.user.domain.UserRepository;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    private final ProductImageRepository productImageRepository;

    private final OrderRepository orderRepository;

    private final OffenderRepository offenderRepository;

    public ProfileService(ProfileRepository profileRepository, UserRepository userRepository,
                          ProductRepository productRepository, ProductImageRepository productImageRepository,
                          OrderRepository orderRepository, OffenderRepository offenderRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.productImageRepository = productImageRepository;
        this.orderRepository = orderRepository;
        this.offenderRepository = offenderRepository;
    }

    public ProfileViewResponse viewProfile(Long userId) {

        User user = userRepository.getById(userId);
        Profile profile = profileRepository.findByUserId(userId).orElse(null);

        ProfileViewResponse profileViewResponse;

        // profile 정보가 없을 경우 주소와 핸드폰 번호는 null 로 반환
        if (profile == null) {
            profileViewResponse = new ProfileViewResponse(user.getId(), user.getProfileImageUrl(), user.getNickname(), user.getEmail(), null, null, null, null);
        }else{
            profileViewResponse = new ProfileViewResponse(user.getId(), user.getProfileImageUrl(), user.getNickname(), user.getEmail(), profile.getAddress(), profile.getMobileNumber(), profile.getAccountType(), profile.getAccountNumber());
        }

        return profileViewResponse;
    }

    public void update(Long userId, ProfileUpdateRequest request) {

        //부정행위자로 등록된 유저는 상품 상세 페이지 들어가지 못하도록
        if(existOffender(userId)) {
            throw new NotAccessProduct();
        }

        User user = userRepository.getById(userId);

        user.updateNickname(request.getNickName());
        userRepository.save(user);

        Profile profile = profileRepository.findByUserId(userId)
                .orElse(null);

        Boolean checkPhone = profileRepository.existsByMobileNumber(request.getMobileNumber());
        if(checkPhone){
            throw new AlreadyExitPhoneProfile();
        }

        if (profile == null) { // 프로필을 등록하지 않은 경우 새로 생성해서 값을 넣어준다.
            profileRepository.save(profile.builder()
                    .user(user)
                    .mobileNumber(request.getMobileNumber())
                    .address(request.getAddress())
                    .accountType(request.getAccountType())
                    .accountNumber(request.getAccountNumber())
                    .build());
        } else { // 기존에 프로필이 있는 경우, 프로필 정보(핸드폰, 주소, 계좌종류, 계좌번호)를 수정해서 저장한다.
            profile.updateMobileNumber(request.getMobileNumber());
            profile.updateAddress(request.getAddress());
            profile.updateAccountType(request.getAccountType());
            profile.updateAccountNumber(request.getAccountNumber());
            profileRepository.save(profile);
        }
    }

    public boolean existOffender(Long userId) {
        Offender offender = offenderRepository.findByUserId(userId).orElse(null);
        return offender != null;
    }

    public ProductViewsSellList sellProductList(Long userId, String productStatus) {
        List<Product> products;

        if(productStatus.equals("ALL")) {
            products = productRepository.findAllByUserId(userId);
        }
        else {
            ProductStatus status = ProductStatus.valueOf(productStatus);
            products = productRepository.findAllByProductStatusAndUserId(status, userId);
        }

        List<ProductViewSellList> responses = products.stream()
                .map(product -> {
                    String image = productImageRepository.findOneImageUrlByProductId(product.getId());
                        ProductViewSellList response = new ProductViewSellList(
                                product.getId(), product.getTitle(), product.getStatus(), product.getSellPrice(), image);

                        return response;
                })
                .collect(Collectors.toList());

        return new ProductViewsSellList(responses);
    }

    public ProductViewsPurchaseList purchaseProductList(Long userId, String confirmStatus) {
        List<Order> orders;

        if(confirmStatus.equals("ALL")) {
            orders = orderRepository.findOrdersAndProductByUserId(userId);
        }
        else {
            ConfirmStatus status = ConfirmStatus.valueOf(confirmStatus);
            orders = orderRepository.findOrdersAndProductByUserIdAndConfirmStatus(userId, status);
        }

        List<ProductViewPurchaseList> responses = orders.stream()
                .map(order -> {
                    String image = productImageRepository.findOneImageUrlByProductId(order.getProduct().getId());
                    ProductViewPurchaseList response = new ProductViewPurchaseList(
                            order.getProduct().getId(), order.getProduct().getTitle(), order.getConfirmStatus(), order.getProduct().getSellPrice(), image);

                    return response;
                })
                .collect(Collectors.toList());

        return new ProductViewsPurchaseList(responses);
    }

    public ProfileBannerResponse viewProfileBanner(Long userId) {
        boolean verifiedBadge = existMobileNumber(userId);
        Long dealCount = sellPurchaseCount(userId);
        Long banCount = userBanCount(userId);

        ProfileBannerResponse response = new ProfileBannerResponse(verifiedBadge, dealCount, banCount);

        return response;
    }

    private boolean existMobileNumber(Long userId) {
        Profile profile = profileRepository.findByUserId(userId).orElse(null);
        return profile != null;
    }

    private Long sellPurchaseCount(Long userId) {
        Long sellCount = productRepository.findCountByProductStatusAndUserId(ProductStatus.COMPLETED, userId);
        Long purchaseCount = orderRepository.findCountByConfirmStatusAndUserId(ConfirmStatus.COMPLETED, userId);
        Long dealCount = sellCount + purchaseCount;

        return dealCount;
    }

    private Long userBanCount(Long userId) {
        Long banCount = userRepository.findBanById(userId);

        return banCount;
    }
}
