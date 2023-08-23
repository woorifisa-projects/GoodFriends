package woorifisa.goodfriends.backend.auth.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import woorifisa.goodfriends.backend.auth.application.AuthService;
import woorifisa.goodfriends.backend.auth.application.OAuthClient;
import woorifisa.goodfriends.backend.auth.application.OAuthUri;
import woorifisa.goodfriends.backend.auth.dto.OAuthUser;
import woorifisa.goodfriends.backend.auth.dto.request.TokenRequest;
import woorifisa.goodfriends.backend.auth.dto.response.AccessTokenResponse;
import woorifisa.goodfriends.backend.auth.dto.response.OAuthUriResponse;

import javax.validation.Valid;

@RequestMapping("/api/auth")
@RestController
public class AuthController {

    private final OAuthUri oAuthUri;

    private final OAuthClient oAuthClient;

    private final AuthService authService;

    public AuthController(OAuthUri oAuthUri, OAuthClient oAuthClient, AuthService authService) {
        this.oAuthUri = oAuthUri;
        this.oAuthClient = oAuthClient;
        this.authService = authService;
    }

    // 로그인 요청
    @GetMapping("/{oauthProvider}/oauth-uri")
    public ResponseEntity<OAuthUriResponse> generateLink(@PathVariable final String oauthProvider,
                                                         @RequestParam final String redirectUri) {
        OAuthUriResponse oAuthUriResponse = new OAuthUriResponse(oAuthUri.generate(redirectUri));
        return ResponseEntity.ok(oAuthUriResponse);
    }

    // 액세스 토큰은 Body로 발급
    @PostMapping("/{oauthProvider}/token")
    public ResponseEntity <AccessTokenResponse> generateAccessToken(
            @PathVariable final String oauthProvider, @Valid @RequestBody final TokenRequest tokenRequest) {
        OAuthUser oAuthUser = oAuthClient.getOAuthUser(tokenRequest.getCode(), tokenRequest.getRedirectUri());
        AccessTokenResponse response = authService.generateAccessToken(oAuthUser);
        return ResponseEntity.ok(response);
    }
}
