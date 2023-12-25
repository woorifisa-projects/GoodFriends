package woorifisa.goodfriends.backend.profile.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import woorifisa.goodfriends.backend.order.domain.OrderStatus;
import woorifisa.goodfriends.backend.order.domain.Order;
import woorifisa.goodfriends.backend.order.domain.OrderRepository;
import woorifisa.goodfriends.backend.product.domain.Product;
import woorifisa.goodfriends.backend.product.domain.ProductImageRepository;
import woorifisa.goodfriends.backend.product.domain.ProductRepository;
import woorifisa.goodfriends.backend.product.domain.ProductStatus;
import woorifisa.goodfriends.backend.profile.dto.response.*;
import woorifisa.goodfriends.backend.profile.domain.Profile;
import woorifisa.goodfriends.backend.profile.domain.ProfileRepository;
import woorifisa.goodfriends.backend.profile.dto.request.ProfileUpdateRequest;
import woorifisa.goodfriends.backend.profile.dto.response.ProfileDetailResponse;
import woorifisa.goodfriends.backend.profile.exception.AlreadyExitPhoneProfileException;
import woorifisa.goodfriends.backend.user.domain.User;
import woorifisa.goodfriends.backend.user.domain.UserRepository;
import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
public class ProfileService {

    private static final String PRODUCT_STATUS_ALL = "ALL"; // 판매 및 구매 목록 상태: 전체
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final OrderRepository orderRepository;

    public ProfileService(final ProfileRepository profileRepository, final UserRepository userRepository,
                          final ProductRepository productRepository, final ProductImageRepository productImageRepository,
                          final OrderRepository orderRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.productImageRepository = productImageRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public ProfileDetailResponse findMyProfile(final Long userId) {
        User user = userRepository.getByUserId(userId);
        Profile profile = profileRepository.findByUserId(userId).orElse(null);

        return ProfileDetailResponse.of(user, profile);
    }

    @Transactional
    public void update(final Long userId, final ProfileUpdateRequest request) {
        User user = userRepository.getByUserId(userId);
        user.updateNickname(request.getNickName());
        userRepository.save(user);

        Profile profile = profileRepository.findByUserId(userId).orElse(null);
        if (profile == null) {
            validateMobileNumber(request.getMobileNumber());
            createProfileInfo(request, user, profile);
        }
        else {
            updateProfileInfo(request, profile);
        }
    }

    private void validateMobileNumber(String mobileNumber) {
        boolean checkPhone = profileRepository.existsByMobileNumber(mobileNumber);
        if(checkPhone) {
            throw new AlreadyExitPhoneProfileException();
        }
    }

    private Profile createProfileInfo(final ProfileUpdateRequest request, final User user, final Profile profile) {
        return profileRepository.save(profile.builder()
                .user(user)
                .mobileNumber(request.getMobileNumber())
                .address(request.getAddress())
                .accountType(request.getAccountType())
                .accountNumber(request.getAccountNumber())
                .build());
    }

    private void updateProfileInfo(final ProfileUpdateRequest request, final Profile profile) {
        profile.updateMobileNumber(request.getMobileNumber());
        profile.updateAddress(request.getAddress());
        profile.updateAccountType(request.getAccountType());
        profile.updateAccountNumber(request.getAccountNumber());
        profileRepository.save(profile);
    }
    public ProfileSellsResponse findProfileSells(final Long userId, final String productStatus) {
        List<Product> products;

        if(productStatus.equals(PRODUCT_STATUS_ALL)) {
            products = productRepository.findAllByUserId(userId);
        } else {
            ProductStatus status = ProductStatus.valueOf(productStatus);
            products = productRepository.findAllByProductStatusAndUserId(status, userId);
        }

        List<ProfileSellResponse> responses = products.stream()
                .map(this::findProductToProfileSellOne)
                .collect(Collectors.toList());
        return new ProfileSellsResponse(responses);
    }

    private ProfileSellResponse findProductToProfileSellOne(final Product product) {
        String image = productImageRepository.findOneImageUrlByProductId(product.getId());
        return ProfileSellResponse.builder()
                .productId(product.getId())
                .title(product.getTitle())
                .status(product.getStatus())
                .sellPrice(product.getSellPrice())
                .imageUrl(image)
                .build();
    }
    public ProfilePurchasesResponse findProfilePurchases(final Long userId, final String confirmStatus) {
        List<Order> orders;

        if(confirmStatus.equals(PRODUCT_STATUS_ALL)) {
            orders = orderRepository.findOrdersAndProductByUserId(userId);
        } else {
            OrderStatus status = OrderStatus.valueOf(confirmStatus);
            orders = orderRepository.findOrdersAndProductByUserIdAndConfirmStatus(userId, status);
        }

        List<ProfilePurchaseResponse> responses = orders.stream()
                .map(order -> mapOrderToProfilePurchaseResponse(order))
                .collect(Collectors.toList());
        return new ProfilePurchasesResponse(responses);
    }

    private ProfilePurchaseResponse mapOrderToProfilePurchaseResponse(final Order order) {
        String image = productImageRepository.findOneImageUrlByProductId(order.getProduct().getId());
        return buildProfilePurchaseResponse(order, image);
    }

    private ProfilePurchaseResponse buildProfilePurchaseResponse(final Order order, final String image) {
        return ProfilePurchaseResponse.builder()
                .productId(order.getProduct().getId())
                .title(order.getProduct().getTitle())
                .status(order.getOrderStatus())
                .sellPrice(order.getProduct().getSellPrice())
                .imageUrl(image)
                .build();
    }

    public ProfileBannerResponse findMyProfileBanner(final Long userId) {
        final boolean verifiedBadge = existMobileNumber(userId);
        final Long orderDealCount = sellPurchaseCount(userId);
        final Long reportBanCount = userRepository.findBanById(userId);

        ProfileBannerResponse response = new ProfileBannerResponse(verifiedBadge, orderDealCount, reportBanCount);
        return response;
    }

    private boolean existMobileNumber(final Long userId) {
        Profile profile = profileRepository.findByUserId(userId).orElse(null);
        return profile != null;
    }

    private Long sellPurchaseCount(final Long userId) {
        Long sellCount = productRepository.findCountByProductStatusAndUserId(ProductStatus.COMPLETED, userId);
        Long purchaseCount = orderRepository.findCountByConfirmStatusAndUserId(OrderStatus.COMPLETED, userId);
        return sellCount + purchaseCount;
    }
}
