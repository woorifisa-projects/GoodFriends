package woorifisa.goodfriends.backend.order.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import woorifisa.goodfriends.backend.auth.dto.LoginUser;
import woorifisa.goodfriends.backend.auth.presentation.AuthenticationPrincipal;
import woorifisa.goodfriends.backend.order.application.OrderService;
import woorifisa.goodfriends.backend.order.dto.request.OrderSaveRequest;
import woorifisa.goodfriends.backend.order.dto.response.OrdersProductResponse;
import woorifisa.goodfriends.backend.user.dto.response.OrderWithUserResponse;

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

    @GetMapping("/{productId}") // 본인이 판매 등록한 물품에 들어온 주문서 전체를 조회하는데 사용됨
    public ResponseEntity<OrdersProductResponse> findAllMyProductOrders(@AuthenticationPrincipal final LoginUser loginUser,
                                                                        @PathVariable final Long productId) {
        OrdersProductResponse responses = orderService.findAllMyProductOrders(loginUser.getId(),productId);

        return ResponseEntity.ok().body(responses);
    }

    @PatchMapping("/deal/{orderId}")
    public ResponseEntity<OrderWithUserResponse> updateOrder(@PathVariable final Long orderId) {
        OrderWithUserResponse response = orderService.updateOrder(orderId);
        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/deal/complete/{productId}")
    public ResponseEntity<Void> updateOrderComplete(@PathVariable final Long productId){
        orderService.updateOrderComplete(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
