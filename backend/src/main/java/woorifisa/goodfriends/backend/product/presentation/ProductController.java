package woorifisa.goodfriends.backend.product.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import woorifisa.goodfriends.backend.product.application.ProductService;
import woorifisa.goodfriends.backend.product.dto.request.ProductSaveRequest;
import woorifisa.goodfriends.backend.product.dto.response.ProductSaveResponse;

import java.net.URI;

@RequestMapping("/api/products")
@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<ProductSaveResponse> saveProduct(@PathVariable Long userId,
                                                           @RequestBody ProductSaveRequest request) {
        ProductSaveResponse response = productService.saveProduct(userId, request);
        return ResponseEntity.created(URI.create("/products/" + response.getId())).body(response);
    }
}
