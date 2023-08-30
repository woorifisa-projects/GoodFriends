package woorifisa.goodfriends.backend.order.application;

import org.springframework.stereotype.Service;
import woorifisa.goodfriends.backend.order.domain.Order;
import woorifisa.goodfriends.backend.order.domain.OrderRepository;
import woorifisa.goodfriends.backend.order.dto.request.OrderSaveRequest;
import woorifisa.goodfriends.backend.product.domain.Product;
import woorifisa.goodfriends.backend.product.domain.ProductRepository;
import woorifisa.goodfriends.backend.user.domain.User;
import woorifisa.goodfriends.backend.user.domain.UserRepository;

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

    public Long saveOrder(OrderSaveRequest request) {
        Product foundProduct = productRepository.getById(request.getProductId());
        User foundUser = userRepository.getById(request.getUserId());
        Order newOrder = createOrder(foundProduct, foundUser, request);
        return newOrder.getId();
    }

    private Order createOrder(Product product, User user, OrderSaveRequest request) {
        return orderRepository.save(Order.builder()
                        .product(product)
                        .user(user)
                        .confirm(false)
                        .possibleDate(request.getPossibleDateStart() + " ~ " + request.getPossibleDateEnd())
                        .possibleTime(request.getPossibleTimeStart() + " ~ " + request.getPossibleTimeEnd())
                        .requirements(request.getRequirements())
                        .build());
    }
}
