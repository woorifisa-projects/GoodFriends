package woorifisa.goodfriends.backend.order.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import woorifisa.goodfriends.backend.auth.dto.LoginUser;
import woorifisa.goodfriends.backend.auth.presentation.AuthenticationPrincipal;
import woorifisa.goodfriends.backend.order.application.OrderService;
import woorifisa.goodfriends.backend.order.dto.request.OrderSaveRequest;
import woorifisa.goodfriends.backend.order.dto.response.OrderViewAllResponse;
import woorifisa.goodfriends.backend.order.dto.response.OrderViewOneResponse;
import woorifisa.goodfriends.backend.user.dto.response.UserDealResponse;

import java.net.URI;
import java.util.List;

@RequestMapping("/api/orders")
@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Long> saveOrder(@AuthenticationPrincipal LoginUser loginUser,
                                          @RequestBody OrderSaveRequest request) {
        Long orderId = orderService.saveOrder(loginUser.getId(), request);
        return ResponseEntity.created(URI.create("/orders/" + orderId)).build();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<OrderViewAllResponse> viewAllOrder(@AuthenticationPrincipal LoginUser loginUser,
                                                                   @PathVariable Long productId) {
        OrderViewAllResponse responses = orderService.viewAllOrder(productId);
        return ResponseEntity.ok().body(responses);
    }

    @PostMapping("/deal/{orderId}")
    public ResponseEntity<UserDealResponse> dealOrder(@AuthenticationPrincipal LoginUser loginUser,
                                                      @PathVariable Long orderId) {
        UserDealResponse response = orderService.dealOrder(orderId);
        return ResponseEntity.ok().body(response);
    }
}
