package woorifisa.goodfriends.backend.product.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import woorifisa.goodfriends.backend.product.application.ProductService;
import woorifisa.goodfriends.backend.product.dto.request.ProductSaveRequest;
import woorifisa.goodfriends.backend.product.dto.request.ProductUpdateRequest;
import woorifisa.goodfriends.backend.product.dto.response.ProductSaveResponse;
import woorifisa.goodfriends.backend.product.dto.response.ProductViewAllResponse;
import woorifisa.goodfriends.backend.product.dto.response.ProductUpdateResponse;
import woorifisa.goodfriends.backend.product.dto.response.ProductViewOneResponse;

import java.net.URI;
import java.util.List;

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

    @GetMapping("/view")
    public ResponseEntity<List<ProductViewAllResponse>> viewAllProduct() {
        List<ProductViewAllResponse> responses = productService.viewAllProduct();
        return ResponseEntity.ok().body(responses);
    }

    @GetMapping("/view/{productId}")
    public ResponseEntity<ProductViewOneResponse> viewOneProduct(@PathVariable Long productId) {
        ProductViewOneResponse response = productService.viewOneProduct(productId);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/edit/{productId}")
    public ResponseEntity<ProductUpdateResponse> showSelectedProduct(@PathVariable Long productId){
        ProductUpdateResponse response = productService.showSelectedProduct(productId);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/edit/{productId}")
    public ResponseEntity<ProductUpdateResponse> updateProduct(@PathVariable Long productId,
                                                               @RequestBody ProductUpdateRequest request) {
        ProductUpdateResponse response = productService.updateProduct(request, productId);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId) {
        productService.deleteById(productId);
        return ResponseEntity.ok().body(productId+": delete");
    }
}
