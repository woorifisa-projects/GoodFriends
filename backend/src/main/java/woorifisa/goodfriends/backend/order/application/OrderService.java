package woorifisa.goodfriends.backend.order.application;

import org.springframework.stereotype.Service;
import woorifisa.goodfriends.backend.offender.domain.Offender;
import woorifisa.goodfriends.backend.offender.domain.OffenderRepository;
import woorifisa.goodfriends.backend.order.domain.OrderStatus;
import woorifisa.goodfriends.backend.order.domain.Order;
import woorifisa.goodfriends.backend.order.domain.OrderRepository;
import woorifisa.goodfriends.backend.order.dto.request.OrderSaveRequest;
import woorifisa.goodfriends.backend.order.exception.NotOwnProductException;
import woorifisa.goodfriends.backend.order.exception.ProductOwnerNotRegisterOrderException;
import woorifisa.goodfriends.backend.product.exception.NotAccessProductException;
import woorifisa.goodfriends.backend.profile.domain.Profile;
import woorifisa.goodfriends.backend.profile.exception.NotFoundProfileException;
import woorifisa.goodfriends.backend.user.dto.response.UserDealResponse;
import woorifisa.goodfriends.backend.order.dto.response.OrderViewAllResponse;
import woorifisa.goodfriends.backend.order.dto.response.OrderViewOneResponse;
import woorifisa.goodfriends.backend.order.exception.AlreadyOrderedException;
import woorifisa.goodfriends.backend.product.domain.Product;
import woorifisa.goodfriends.backend.product.domain.ProductRepository;
import woorifisa.goodfriends.backend.product.domain.ProductStatus;
import woorifisa.goodfriends.backend.profile.domain.ProfileRepository;
import woorifisa.goodfriends.backend.user.domain.User;
import woorifisa.goodfriends.backend.user.domain.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    public final OrderRepository orderRepository;
    public final ProductRepository productRepository;
    public final UserRepository userRepository;
    public final ProfileRepository profileRepository;
    public final OffenderRepository offenderRepository;

    public OrderService(final OrderRepository orderRepository, final ProductRepository productRepository,
                        final UserRepository userRepository, final ProfileRepository profileRepository,
                        final OffenderRepository offenderRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.offenderRepository = offenderRepository;
    }

    public Long saveOrder(final Long userId, final OrderSaveRequest request) {
        validateUser(userId);
        validateOrder(request, userId);

        Product foundProduct = productRepository.getById(request.getProductId());
        User foundUser = userRepository.getById(userId);

        Order newOrder = orderRepository.save(createOrder(foundProduct, foundUser, request));
        return newOrder.getId();
    }


    private void validateUser(final Long userId) {
        if (doesOffenderExist(userId)) {
            throw new NotAccessProductException();
        }
        if (!doesProfileExist(userId)) {
            throw new NotFoundProfileException();
        }
    }

    private boolean doesOffenderExist(final Long userId) {
        Offender offender = offenderRepository.findByUserId(userId);
        return offender != null;
    }

    private boolean doesProfileExist(final Long userId) {
        Profile profile = profileRepository.findByUserId(userId).orElse(null);
        return profile != null;
    }

    private void validateOrder(final OrderSaveRequest request, final Long userId) {
        if (isOrderDuplicated(request.getProductId(), userId)) {
            throw new AlreadyOrderedException();
        }

        if (isProductOwnedByUser(request.getProductId(), userId)) {
            throw new ProductOwnerNotRegisterOrderException();
        }
    }

    private boolean isOrderDuplicated(final Long productId, final Long userId) {
        Order order = orderRepository.findByProductIdAndUserId(productId, userId);
        return order != null;
    }

    private boolean isProductOwnedByUser(final Long productId, final Long userId) {
        Product product = productRepository.getById(productId);
        return product.getUser().getId().equals(userId);
    }

    private Order createOrder(final Product product, final User user, final OrderSaveRequest request) {
        Order newOrder = Order.builder()
                .product(product)
                .user(user)
                .orderStatus(OrderStatus.ORDERING)
                .possibleDate(request.getPossibleDateStart() + " ~ " + request.getPossibleDateEnd())
                .possibleTime(request.getPossibleTimeStart() + " ~ " + request.getPossibleTimeEnd())
                .requirements(request.getRequirements())
                .build();
        return newOrder;
    }

    public OrderViewAllResponse findAllOrder(final Long userId, final Long productId) {

        // 부정행위자 본인이 등록한 상품 주문서 조회 불가
        if (doesOffenderExist(userId)) {
            throw new NotAccessProductException();
        }

        // 본인이 등록한 상품만 주문서 조회 가능
        if (!isProductOwnedByUser(productId, userId)) {
            throw new NotOwnProductException();
        }

        Product product = productRepository.getById(productId);

        if (product.getStatus() != ProductStatus.SELL) {
            Order order = orderRepository.findByProductIdAndConfirmStatus(productId, OrderStatus.RESERVATION);

            if (order == null) {
                order = orderRepository.findByProductIdAndConfirmStatus(productId, OrderStatus.COMPLETED);
            }

            OrderViewOneResponse response = new OrderViewOneResponse(order.getId(), order.getUser().getId(), order.getUser().getProfileImageUrl(),
                    order.getUser().getNickname(), order.getPossibleDate(), order.getPossibleTime(), order.getRequirements());
            List<OrderViewOneResponse> responses = List.of(response);

            return new OrderViewAllResponse(responses, true);
        }

        List<OrderViewOneResponse> responses = orderRepository.findOrdersAndUserByProductId(productId).stream()
                .map(order -> {
                    OrderViewOneResponse response = new OrderViewOneResponse(order.getId(), order.getUser().getId(), order.getUser().getProfileImageUrl(),
                            order.getUser().getNickname(), order.getPossibleDate(), order.getPossibleTime(), order.getRequirements());
                    return response;
                })
                .collect(Collectors.toList());

        return new OrderViewAllResponse(responses, false);
    }

    @Transactional
    public UserDealResponse updateOrder(final Long orderId) {

        Order order = orderRepository.getById(orderId);
        Product product = productRepository.getById(order.getProduct().getId());

        if (product.getStatus() == ProductStatus.SELL) {
            orderRepository.updateOrderStatus(orderId, OrderStatus.RESERVATION);
            productRepository.updateProductStatus(order.getProduct().getId(), ProductStatus.RESERVATION);
        }

        User user = userRepository.getById(order.getUser().getId());

        UserDealResponse response = new UserDealResponse(user.getNickname(), user.getProfileImageUrl(), user.getEmail());

        return response;
    }

    @Transactional
    public void updateOrderConfirmDeal(final Long productId) {
        productRepository.updateProductStatus(productId, ProductStatus.COMPLETED);
        Order order = orderRepository.findByProductIdAndConfirmStatus(productId, OrderStatus.RESERVATION);
        orderRepository.updateOrderStatus(order.getId(), OrderStatus.COMPLETED);
    }

}
