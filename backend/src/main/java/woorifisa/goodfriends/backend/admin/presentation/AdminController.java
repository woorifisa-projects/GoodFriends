package woorifisa.goodfriends.backend.admin.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import woorifisa.goodfriends.backend.admin.application.AdminService;
import woorifisa.goodfriends.backend.admin.dto.request.AdminLoginRequest;
import woorifisa.goodfriends.backend.product.dto.request.ProductSaveRequest;
import woorifisa.goodfriends.backend.product.dto.response.ProductSaveResponse;
import woorifisa.goodfriends.backend.product.dto.response.ProductViewAllResponse;
import woorifisa.goodfriends.backend.product.dto.response.ProductViewOneResponse;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/admin/")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AdminLoginRequest adminLoginRequest) {

        String token = adminService.login(adminLoginRequest.getAdminId(), adminLoginRequest.getPassword());
        return ResponseEntity.ok().body(token);
    }

    @PostMapping("/products/new")
    public ResponseEntity<ProductSaveResponse> saveProduct(Authentication authentication,
                                                           @RequestPart ProductSaveRequest request,
                                                           @RequestPart List<MultipartFile> multipartFiles) throws IOException {
        String adminId = authentication.getName();
        ProductSaveRequest productSaveRequest = new ProductSaveRequest(request.getTitle(), request.getProductCategory(),request.getDescription(), request.getSellPrice(), multipartFiles);
        ProductSaveResponse response = adminService.saveProduct(adminId, productSaveRequest);
        return ResponseEntity.created(URI.create("/products/" + response.getId())).body(response);
    }

    @GetMapping("/products/view")
    public ResponseEntity<List<ProductViewAllResponse>> viewAllProduct() {
        List<ProductViewAllResponse> responses = adminService.viewAllProduct();
        return ResponseEntity.ok().body(responses);
    }

    @GetMapping("/products/view/{productId}")
    public ResponseEntity<ProductViewOneResponse> viewOneProduct(@PathVariable Long productId) {
        ProductViewOneResponse response = adminService.viewOneProduct(productId);
        return ResponseEntity.ok().body(response);
    }

}