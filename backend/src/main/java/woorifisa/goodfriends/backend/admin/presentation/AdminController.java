package woorifisa.goodfriends.backend.admin.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import woorifisa.goodfriends.backend.admin.application.AdminService;
import woorifisa.goodfriends.backend.admin.dto.request.AdminLoginRequest;
import woorifisa.goodfriends.backend.admin.dto.request.UserUpdateRequest;
import woorifisa.goodfriends.backend.admin.dto.response.UserLogRecordsResponse;
import woorifisa.goodfriends.backend.auth.dto.response.AccessTokenResponse;
import woorifisa.goodfriends.backend.product.dto.request.ProductSaveRequest;
import woorifisa.goodfriends.backend.product.dto.request.ProductUpdateRequest;
import woorifisa.goodfriends.backend.product.dto.response.ProductUpdateResponse;
import woorifisa.goodfriends.backend.product.dto.response.ProductViewAllResponse;
import woorifisa.goodfriends.backend.product.dto.response.ProductViewOneResponse;
import woorifisa.goodfriends.backend.product.dto.response.ProductViewsAllResponse;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/login")
    public ResponseEntity<AccessTokenResponse> login(@RequestBody AdminLoginRequest adminLoginRequest) {

        AccessTokenResponse response = adminService.login(adminLoginRequest.getRoot(), adminLoginRequest.getPassword());

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/products/new")
    public ResponseEntity<Void> saveProduct(Authentication authentication,
                                                           @RequestPart ProductSaveRequest request,
                                                           @RequestPart List<MultipartFile> multipartFiles) throws IOException {
        long adminId = Long.parseLong(authentication.getName());
        ProductSaveRequest productSaveRequest = new ProductSaveRequest(request.getTitle(), request.getProductCategory(),request.getDescription(), request.getSellPrice(), multipartFiles);
        Long productId = adminService.saveProduct(adminId, productSaveRequest);
        return ResponseEntity.created(URI.create("/products/" + productId)).build(); // 201
    }

    @GetMapping("/products/view")
    public ResponseEntity<ProductViewsAllResponse> viewAllProduct() {
        ProductViewsAllResponse responses = adminService.viewAllProduct();
        return ResponseEntity.ok().body(responses);
    }

    @GetMapping("/products/view/{productId}")
    public ResponseEntity<ProductViewOneResponse> viewOneProduct(@PathVariable Long productId) {
        ProductViewOneResponse response = adminService.viewOneProduct(productId);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/products/edit/{productId}")
    public ResponseEntity<ProductUpdateResponse> showSelectedProduct(@PathVariable Long productId){
        ProductUpdateResponse response = adminService.showSelectedProduct(productId);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/products/edit/{productId}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long productId,
                                                               @RequestPart ProductUpdateRequest request,
                                                               @RequestPart List<MultipartFile> multipartFiles) throws IOException {
        ProductUpdateRequest productUpdateRequest = new ProductUpdateRequest(request.getTitle(), request.getProductCategory(), request.getDescription(), request.getSellPrice(), multipartFiles);
        ProductUpdateResponse response = adminService.updateProduct(productUpdateRequest, productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/products/delete/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) throws MalformedURLException {
        adminService.deleteById(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //관리자가 사용자들 로그기록 조회
    @GetMapping("/user-log/record")
    public ResponseEntity<UserLogRecordsResponse> entryRecord() {
        UserLogRecordsResponse response = adminService.findUserLogRecord();
        return ResponseEntity.ok(response);
    }

    //관리자가 사용자 정보를 삭제
    @DeleteMapping("/user/delete/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId){

        adminService.deleteUserInfo(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
    }

    //관리자가 사용자 정보 업데이트
    @PutMapping("/user/edit/{userId}")
    public ResponseEntity<Void> updateUserInfo(@PathVariable Long userId, @RequestBody UserUpdateRequest userUpdateRequest){
        adminService.updateUserInfo(userId,userUpdateRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204

    }
}