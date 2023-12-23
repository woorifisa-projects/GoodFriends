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

@Transactional
@Service
public class ProfileService {
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

    public ProfileDetailResponse findMyProfile(final Long userId) {
        User user = userRepository.getById(userId);
        Profile profile = profileRepository.findByUserId(userId).orElse(null);

        return ProfileDetailResponse.of(user, profile);
    }

    public void update(final Long userId, final ProfileUpdateRequest request) {
        User user = userRepository.getById(userId);
        user.updateNickname(request.getNickName());
        userRepository.save(user);

        Profile profile = profileRepository.getByUserId(userId);
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

    public ProfileViewsSellList sellProductList(final Long userId, final String productStatus) {
        List<Product> products;

        if(productStatus.equals("ALL")) {
            products = productRepository.findAllByUserId(userId);
        }
        else {
            ProductStatus status = ProductStatus.valueOf(productStatus);
            products = productRepository.findAllByProductStatusAndUserId(status, userId);
        }

        List<ProfileViewSellList> responses = products.stream()
                .map(product -> {
                    String image = productImageRepository.findOneImageUrlByProductId(product.getId());
                        ProfileViewSellList response = new ProfileViewSellList(
                                product.getId(), product.getTitle(), product.getStatus(), product.getSellPrice(), image);

                        return response;
                })
                .collect(Collectors.toList());

        return new ProfileViewsSellList(responses);
    }

    public ProfileViewsPurchaseList purchaseProductList(final Long userId, final String confirmStatus) {
        List<Order> orders;

        if(confirmStatus.equals("ALL")) {
            orders = orderRepository.findOrdersAndProductByUserId(userId);
        }
        else {
            ConfirmStatus status = ConfirmStatus.valueOf(confirmStatus);
            orders = orderRepository.findOrdersAndProductByUserIdAndConfirmStatus(userId, status);
        }

        List<ProfileViewPurchaseList> responses = orders.stream()
                .map(order -> {
                    String image = productImageRepository.findOneImageUrlByProductId(order.getProduct().getId());
                    ProfileViewPurchaseList response;
                    return getProductViewPurchaseList(order, image);
                })
                .collect(Collectors.toList());

        return new ProfileViewsPurchaseList(responses);
    }

    private ProfileViewPurchaseList getProductViewPurchaseList(Order order, String image) {
        return new ProfileViewPurchaseList(
                order.getProduct().getId(),
                order.getProduct().getTitle(),
                order.getConfirmStatus(),
                order.getProduct().getSellPrice(),
                image
        );
    }

    public ProfileBannerResponse viewProfileBanner(final Long userId) {
        boolean verifiedBadge = existMobileNumber(userId);
        Long dealCount = sellPurchaseCount(userId);
        Long banCount = userRepository.findBanById(userId);

        ProfileBannerResponse response = new ProfileBannerResponse(verifiedBadge, dealCount, banCount);

        return response;
    }

    private boolean existMobileNumber(final Long userId) {
        Profile profile = profileRepository.findByUserId(userId).orElse(null);
        return profile != null;
    }

    private Long sellPurchaseCount(final Long userId) {
        Long sellCount = productRepository.findCountByProductStatusAndUserId(ProductStatus.COMPLETED, userId);
        Long purchaseCount = orderRepository.findCountByConfirmStatusAndUserId(ConfirmStatus.COMPLETED, userId);
        return sellCount + purchaseCount;
    }
}
