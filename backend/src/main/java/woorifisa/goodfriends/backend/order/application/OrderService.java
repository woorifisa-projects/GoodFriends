package woorifisa.goodfriends.backend.order.application;

import org.springframework.stereotype.Service;
import woorifisa.goodfriends.backend.order.domain.ConfirmStatus;
import woorifisa.goodfriends.backend.order.domain.Order;
import woorifisa.goodfriends.backend.order.domain.OrderRepository;
import woorifisa.goodfriends.backend.order.dto.request.OrderSaveRequest;
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

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public Long saveOrder(Long userId, OrderSaveRequest request) {

        if(orderRepository.findByProductIdAndUserId(request.getProductId(), userId) != null){
            throw new AlreadyOrderedException();
        }

        Product foundProduct = productRepository.getById(request.getProductId());
        User foundUser = userRepository.getById(userId);

        Order newOrder = orderRepository.save(createOrder(foundProduct, foundUser, request));
        return newOrder.getId();
    }

    private Order createOrder(Product product, User user, OrderSaveRequest request) {
        Order newOrder = Order.builder()
                        .product(product)
                        .user(user)
                        .confirmStatus(ConfirmStatus.ORDERING)
                        .possibleDate(request.getPossibleDateStart() + " ~ " + request.getPossibleDateEnd())
                        .possibleTime(request.getPossibleTimeStart() + " ~ " + request.getPossibleTimeEnd())
                        .requirements(request.getRequirements())
                        .build();
        return newOrder;
    }

    public OrderViewAllResponse viewAllOrder(Long productId) {

        List<OrderViewOneResponse> responses = orderRepository.findOrdersAndUserByProductId(productId).stream()
                .map(order -> {
                    OrderViewOneResponse response =  new OrderViewOneResponse(order.getId(), order.getUser().getId(), order.getUser().getProfileImageUrl(), order.getUser().getNickname(), order.getPossibleDate(), order.getPossibleTime(), order.getRequirements());
                    return response;
                })
                .collect(Collectors.toList());

        return new OrderViewAllResponse(responses);
    }

    @Transactional
    public UserDealResponse dealOrder(Long orderId) {

        orderRepository.updateConfirmStatus(orderId, ConfirmStatus.COMPLETED);

        Order order = orderRepository.getById(orderId);
        productRepository.updateProductStatus(order.getProduct().getId(), ProductStatus.RESERVATION);
        User user = userRepository.getById(order.getUser().getId());

        UserDealResponse response = new UserDealResponse(user.getNickname(), user.getProfileImageUrl(), user.getEmail());

        return response;
    }

}
