package woorifisa.goodfriends.backend.order.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import woorifisa.goodfriends.backend.auth.dto.LoginUser;
import woorifisa.goodfriends.backend.auth.presentation.AuthenticationPrincipal;
import woorifisa.goodfriends.backend.order.application.OrderService;
import woorifisa.goodfriends.backend.order.dto.request.OrderSaveRequest;
import woorifisa.goodfriends.backend.order.dto.response.OrdersProductResponse;
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

    @GetMapping("/{productId}") // 본인이 판매 등록한 물품에 들어온 주문서 전체를 조회하는데 사용됨
    public ResponseEntity<OrdersProductResponse> findAllMyProductOrders(@AuthenticationPrincipal final LoginUser loginUser,
                                                                        @PathVariable final Long productId) {
        OrdersProductResponse responses = orderService.findAllMyProductOrders(loginUser.getId(),productId);

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
