package woorifisa.goodfriends.backend.admin.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import woorifisa.goodfriends.backend.admin.application.AdminService;
import woorifisa.goodfriends.backend.admin.dto.request.AdminLoginRequest;
import woorifisa.goodfriends.backend.admin.dto.request.UserUpdateRequest;
import woorifisa.goodfriends.backend.admin.dto.response.UserInfoResponse;
import woorifisa.goodfriends.backend.admin.dto.response.UserLogRecordsResponse;
import woorifisa.goodfriends.backend.auth.dto.response.AccessTokenResponse;
import woorifisa.goodfriends.backend.product.dto.request.ProductSaveRequest;
import woorifisa.goodfriends.backend.product.dto.request.ProductUpdateRequest;
import woorifisa.goodfriends.backend.product.dto.response.ProductUpdateResponse;
import woorifisa.goodfriends.backend.product.dto.response.ProductViewAllResponse;
import woorifisa.goodfriends.backend.product.dto.response.ProductViewOneResponse;
import woorifisa.goodfriends.backend.product.dto.response.ProductViewsAllResponse;
import woorifisa.goodfriends.backend.report.dto.response.ReportsResponse;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;
import java.util.Objects;

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

    // 사용자 로그기록 전체 조회
    @GetMapping("/log")
    public ResponseEntity<UserLogRecordsResponse> entryRecord() {
        UserLogRecordsResponse response = adminService.findUserLogRecord();
        return ResponseEntity.ok(response);
    }

    // 사용자 정보 전체 조회
    @GetMapping("/user")
    public ResponseEntity<List<UserInfoResponse>> getAllUsers(){
        List<UserInfoResponse> userInfoResponse = adminService.getAllUsers();
        return ResponseEntity.ok().body(userInfoResponse);
    }

    // 사용자 정보 수정
    @PutMapping("/user/edit/{userId}")
    public ResponseEntity<Void> updateUserInfo(@PathVariable Long userId, @RequestBody UserUpdateRequest userUpdateRequest){
        adminService.updateUserInfo(userId,userUpdateRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204

    }

    // 사용자 정보 삭제
    @DeleteMapping("/user/remove/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId){

        adminService.deleteUserInfo(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
    }

    // 상품 등록
    @PostMapping("/products")
    public ResponseEntity<Void> saveProduct(Authentication authentication,
                                                           @RequestPart ProductSaveRequest request,
                                                           @RequestPart List<MultipartFile> multipartFiles) throws IOException {
        long adminId = Long.parseLong(authentication.getName());
        ProductSaveRequest productSaveRequest = new ProductSaveRequest(request.getTitle(), request.getProductCategory(),request.getDescription(), request.getSellPrice(), multipartFiles);
        Long productId = adminService.saveProduct(adminId, productSaveRequest);
        return ResponseEntity.created(URI.create("/products/" + productId)).build(); // 201
    }

    // 상품 검색
    @GetMapping("/products/search")
    public ResponseEntity<ProductViewsAllResponse> viewSearchProduct(@RequestParam String keyword) {
        ProductViewsAllResponse responses = adminService.viewSearchProduct(keyword);
        return ResponseEntity.ok().body(responses); // 200
    }

    // 상품 전체 조회
    @GetMapping("/products")
    public ResponseEntity<ProductViewsAllResponse> viewAllProduct() {
        ProductViewsAllResponse responses = adminService.viewAllProduct();
        return ResponseEntity.ok().body(responses);
    }

    // 상품 상세 조회
    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductViewOneResponse> viewOneProduct(@PathVariable Long productId) {
        ProductViewOneResponse response = adminService.viewOneProduct(productId);
        return ResponseEntity.ok().body(response);
    }

    // 수정할 상품 조회
    @GetMapping("/products/edit/{productId}")
    public ResponseEntity<ProductUpdateResponse> showSelectedProduct(@PathVariable Long productId){
        ProductUpdateResponse response = adminService.showSelectedProduct(productId);
        return ResponseEntity.ok().body(response);
    }

    // 상품 수정
    @PutMapping("/products/edit/{productId}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long productId,
                                                               @RequestPart ProductUpdateRequest request,
                                                               @RequestPart List<MultipartFile> multipartFiles) throws IOException {
        ProductUpdateRequest productUpdateRequest = new ProductUpdateRequest(request.getTitle(), request.getProductCategory(), request.getDescription(), request.getSellPrice(), multipartFiles);
        ProductUpdateResponse response = adminService.updateProduct(productUpdateRequest, productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // 상품 삭제
    @DeleteMapping("/products/remove/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) throws MalformedURLException {
        adminService.deleteById(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    // 상품 신고 전체 조회
    @GetMapping("/report/products")
    public ResponseEntity<ReportsResponse> viewAllProductReports() {
        ReportsResponse reportsResponse = adminService.viewAllProductReports();
        return ResponseEntity.ok().body(reportsResponse); // 200
    }
}