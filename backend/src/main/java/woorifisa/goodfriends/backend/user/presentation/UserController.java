package woorifisa.goodfriends.backend.user.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import woorifisa.goodfriends.backend.auth.dto.LoginUser;
import woorifisa.goodfriends.backend.auth.presentation.AuthenticationPrincipal;
import woorifisa.goodfriends.backend.user.application.UserService;
import woorifisa.goodfriends.backend.user.dto.response.UserResponse;

@RequestMapping("/api/users")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> findMe(@AuthenticationPrincipal final LoginUser loginUser) {
        UserResponse response = userService.findById(loginUser.getId());
        return ResponseEntity.ok(response);

    }
}
