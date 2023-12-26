package woorifisa.goodfriends.backend.order.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import woorifisa.goodfriends.backend.auth.dto.LoginUser;
import woorifisa.goodfriends.backend.auth.presentation.AuthenticationPrincipal;
import woorifisa.goodfriends.backend.order.application.OrderService;
import woorifisa.goodfriends.backend.order.dto.request.OrderSaveRequest;
import woorifisa.goodfriends.backend.order.dto.response.OrderViewAllResponse;
import woorifisa.goodfriends.backend.user.dto.response.UserDealResponse;

import java.net.URI;

@RequestMapping("/api/orders")
@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(final OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Long> saveOrder(@AuthenticationPrincipal final LoginUser loginUser,
                                          @RequestBody final OrderSaveRequest request) {
        Long orderId = orderService.saveOrder(loginUser.getId(), request);
        return ResponseEntity.created(URI.create("/orders/" + orderId)).build();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<OrderViewAllResponse> findAllMyProductOrders(@AuthenticationPrincipal final LoginUser loginUser,
                                                                   @PathVariable final Long productId) {
        OrderViewAllResponse responses = orderService.findAllMyProductOrders(loginUser.getId(),productId);

        return ResponseEntity.ok().body(responses);
    }

    @PatchMapping("/deal/{orderId}")
    public ResponseEntity<UserDealResponse> updateOrder(@PathVariable final Long orderId) {
        UserDealResponse response = orderService.updateOrder(orderId);
        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/deal/complete/{productId}")
    public ResponseEntity<Void> updateOrderConfirmDeal(@PathVariable final Long productId){
        orderService.updateOrderConfirmDeal(productId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
