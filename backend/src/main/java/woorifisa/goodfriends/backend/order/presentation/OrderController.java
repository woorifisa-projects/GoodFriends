package woorifisa.goodfriends.backend.order.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import woorifisa.goodfriends.backend.order.application.OrderService;
import woorifisa.goodfriends.backend.order.dto.request.OrderSaveRequest;
import woorifisa.goodfriends.backend.order.dto.response.OrderViewAllResponse;

import java.util.List;

@RequestMapping("/api/orders")
@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/new")
    public ResponseEntity<Long> saveOrder(@RequestBody OrderSaveRequest request) {
        Long orderId = orderService.saveOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderId);
    }

    @GetMapping("/view/{productId}")
    public ResponseEntity<List<OrderViewAllResponse>> viewAllOrder(@PathVariable Long productId) {
        List<OrderViewAllResponse> responses = orderService.viewAllOrder(productId);
        return ResponseEntity.ok().body(responses);
    }

}
