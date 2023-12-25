package woorifisa.goodfriends.backend.order.application;

import org.springframework.stereotype.Service;
import woorifisa.goodfriends.backend.offender.domain.Offender;
import woorifisa.goodfriends.backend.offender.domain.OffenderRepository;
import woorifisa.goodfriends.backend.order.domain.OrderStatus;
import woorifisa.goodfriends.backend.order.domain.Order;
import woorifisa.goodfriends.backend.order.domain.OrderRepository;
import woorifisa.goodfriends.backend.order.dto.request.OrderSaveRequest;
import woorifisa.goodfriends.backend.order.exception.NotOwnProductException;
import woorifisa.goodfriends.backend.order.exception.OwnProductException;
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

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, UserRepository userRepository, ProfileRepository profileRepository, OffenderRepository offenderRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.offenderRepository = offenderRepository;
    }

    public Long saveOrder(Long userId, OrderSaveRequest request) {

        // 부정행위자 주문 불가
        if(existOffender(userId)) {
            throw new NotAccessProductException();
        }

        // 프로필 미등록자 주문 불가
        if(!existProfile(userId)) {
            throw new NotFoundProfileException();
        }

        // 중복 주문 불가
        if(duplicateOrder(request.getProductId(), userId)) {
            throw new AlreadyOrderedException();
        }

        // 본인 상품 주문서 제출 불가
        if(ownProduct(request.getProductId(), userId)) {
            throw new OwnProductException();
        }

        Product foundProduct = productRepository.getByProductId(request.getProductId());
        User foundUser = userRepository.getByUserId(userId);

        Order newOrder = orderRepository.save(createOrder(foundProduct, foundUser, request));
        return newOrder.getId();
    }

    public boolean existProfile(Long userId) {
        Profile profile = profileRepository.findByUserId(userId).orElse(null);
        return profile != null;
    }

    public boolean existOffender(final Long userId) {
        Offender offender = offenderRepository.findByUserId(userId);
        return offender != null;
    }

    public boolean duplicateOrder(Long productId, Long userId) {
        Order order = orderRepository.findByProductIdAndUserId(productId, userId);
        return order != null;
    }

    public boolean ownProduct(Long productId, Long userId) {
        Product product = productRepository.getByProductId(productId);
        return product.getUser().getId() == userId;
    }

    private Order createOrder(Product product, User user, OrderSaveRequest request) {
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

    public OrderViewAllResponse viewAllOrder(Long userId, Long productId) {

        // 부정행위자 본인이 등록한 상품 주문서 조회 불가
        if(existOffender(userId)) {
            throw new NotAccessProductException();
        }

        // 본인이 등록한 상품만 주문서 조회 가능
        if(!ownProduct(productId, userId)){
            throw new NotOwnProductException();
        }

        Product product = productRepository.getByProductId(productId);

        if(product.getStatus() != ProductStatus.SELL) {
            Order order = orderRepository.findByProductIdAndConfirmStatus(productId, OrderStatus.RESERVATION);

            if(order == null) {
                order = orderRepository.findByProductIdAndConfirmStatus(productId, OrderStatus.COMPLETED);
            }

            OrderViewOneResponse response = new OrderViewOneResponse(order.getId(), order.getUser().getId(), order.getUser().getProfileImageUrl(),
                                                    order.getUser().getNickname(), order.getPossibleDate(), order.getPossibleTime(), order.getRequirements());
            List<OrderViewOneResponse> responses = List.of(response);

            return new OrderViewAllResponse(responses, true);
        }

        List<OrderViewOneResponse> responses = orderRepository.findOrdersAndUserByProductId(productId).stream()
                .map(order -> {
                    OrderViewOneResponse response =  new OrderViewOneResponse(order.getId(), order.getUser().getId(), order.getUser().getProfileImageUrl(),
                                                        order.getUser().getNickname(), order.getPossibleDate(), order.getPossibleTime(), order.getRequirements());
                    return response;
                })
                .collect(Collectors.toList());

        return new OrderViewAllResponse(responses, false);
    }

    @Transactional
    public UserDealResponse dealOrder(Long orderId) {

        Order order = orderRepository.getById(orderId);
        Product product = productRepository.getByProductId(order.getProduct().getId());

        if(product.getStatus() == ProductStatus.SELL) {
            orderRepository.updateOrderStatus(orderId, OrderStatus.RESERVATION);
            productRepository.updateProductStatus(order.getProduct().getId(), ProductStatus.RESERVATION);
        }

        User user = userRepository.getByUserId(order.getUser().getId());

        UserDealResponse response = new UserDealResponse(user.getNickname(), user.getProfileImageUrl(), user.getEmail());

        return response;
    }

    @Transactional
    public void confirmDeal(Long productId){
        productRepository.updateProductStatus(productId,ProductStatus.COMPLETED);
        Order order = orderRepository.findByProductIdAndConfirmStatus(productId, OrderStatus.RESERVATION);
        orderRepository.updateOrderStatus(order.getId(), OrderStatus.COMPLETED);
    }

}
