package woorifisa.goodfriends.backend.product.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import woorifisa.goodfriends.backend.auth.dto.LoginUser;
import woorifisa.goodfriends.backend.auth.presentation.AuthenticationPrincipal;
import woorifisa.goodfriends.backend.product.application.ProductService;
import woorifisa.goodfriends.backend.product.dto.request.ProductSaveRequest;
import woorifisa.goodfriends.backend.product.dto.request.ProductUpdateRequest;
import woorifisa.goodfriends.backend.product.dto.response.ProductSaveResponse;
import woorifisa.goodfriends.backend.product.dto.response.ProductViewAllResponse;
import woorifisa.goodfriends.backend.product.dto.response.ProductUpdateResponse;
import woorifisa.goodfriends.backend.product.dto.response.ProductViewOneResponse;
import woorifisa.goodfriends.backend.user.application.UserService;
import woorifisa.goodfriends.backend.user.dto.response.UserResponse;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@RequestMapping("/api/products")
@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService, UserService userService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Long> saveProduct(@AuthenticationPrincipal final LoginUser loginUser,
                                            @RequestPart ProductSaveRequest request,
                                            @RequestPart List<MultipartFile> multipartFiles) throws IOException {
        ProductSaveRequest productSaveRequest = new ProductSaveRequest(request.getTitle(), request.getProductCategory(),request.getDescription(), request.getSellPrice(), multipartFiles);
        ProductSaveResponse response = productService.saveProduct(loginUser.getId(), productSaveRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response.getId()); // 201
    }

    @GetMapping("/view")
    public ResponseEntity<List<ProductViewAllResponse>> viewAllProduct() {
        List<ProductViewAllResponse> responses = productService.viewAllProduct();
        return ResponseEntity.ok().body(responses); // 200
    }

    @GetMapping("/view/{productId}")
    public ResponseEntity<ProductViewOneResponse> viewOneProduct(@PathVariable Long productId) {
        ProductViewOneResponse response = productService.viewOneProduct(productId);
        return ResponseEntity.ok().body(response); // 200
    }

    @GetMapping("/edit/{productId}")
    public ResponseEntity<ProductUpdateResponse> showSelectedProduct(@AuthenticationPrincipal final LoginUser loginUser,
                                                                     @PathVariable Long productId){
        if(productService.verifyUser(loginUser.getId(), productId)) {
            ProductUpdateResponse response = productService.showSelectedProduct(productId);
            return ResponseEntity.ok().body(response); // 204
        }
        else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN); // 403
        }
    }

    @PutMapping("/edit/{productId}")
    public ResponseEntity<Void> updateProduct(@AuthenticationPrincipal final LoginUser loginUser,
                                              @PathVariable Long productId,
                                              @RequestPart ProductUpdateRequest request,
                                              @RequestPart List<MultipartFile> multipartFiles) throws IOException {
        if(productService.verifyUser(loginUser.getId(), productId)) {
            ProductUpdateRequest productUpdateRequest = new ProductUpdateRequest(request.getTitle(), request.getProductCategory(), request.getDescription(), request.getSellPrice(), multipartFiles);
            productService.updateProduct(productUpdateRequest, productId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
        }
        else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN); // 403
        }
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<Void> deleteProduct(@AuthenticationPrincipal final LoginUser loginUser,
                                              @PathVariable Long productId) throws MalformedURLException {
        if(productService.verifyUser(loginUser.getId(), productId)) {
            productService.deleteById(productId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
        }
        else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN); // 403
        }
    }
}