package woorifisa.goodfriends.backend.admin.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import woorifisa.goodfriends.backend.admin.application.AdminService;
import woorifisa.goodfriends.backend.admin.dto.request.AdminLoginRequest;

@RestController
@RequestMapping("/admin")
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

}