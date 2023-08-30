package woorifisa.goodfriends.backend.auth.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import woorifisa.goodfriends.backend.auth.application.AuthService;
import woorifisa.goodfriends.backend.auth.application.OAuthClient;
import woorifisa.goodfriends.backend.auth.application.OAuthUri;
import woorifisa.goodfriends.backend.auth.dto.LoginUser;
import woorifisa.goodfriends.backend.auth.dto.OAuthUser;
import woorifisa.goodfriends.backend.auth.dto.request.TokenRenewalRequest;
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

    // 액세스 토큰은 Body로 발급, 리프레시 토큰은 Set-Cookie로 발급 받기
    @PostMapping("/{oauthProvider}/token")
    public ResponseEntity <AccessTokenResponse> generateAccessAndRefreshToken(
            @PathVariable final String oauthProvider, @Valid @RequestBody final TokenRequest tokenRequest) {
        OAuthUser oAuthUser = oAuthClient.getOAuthUser(tokenRequest.getCode(), tokenRequest.getRedirectUri());
        AccessTokenResponse response = authService.generateAccessAndRefreshToken(oAuthUser);
        return ResponseEntity.ok(response);
    }

    // 리프레시 토큰을 이용하여 새로운 액세스 토큰을 발급 받기
    @PostMapping("/token/access")
    public ResponseEntity<AccessTokenResponse> generateAccessToken(
            @RequestHeader("refreshToken") String refreshToken) {
        TokenRenewalRequest tokenRenewalRequest = new TokenRenewalRequest(refreshToken);
        AccessTokenResponse response = authService.generateAccessToken(tokenRenewalRequest);
        return ResponseEntity.ok(response);
    }

    // 로그아웃: 로그아웃 시, 서버에서 accessToken과 refreshToken값을 만료시킨다.
    @GetMapping("/logout")
    public ResponseEntity<Void> logout(@AuthenticationPrincipal LoginUser loginUser) {
        authService.deleteToken(loginUser.getId());
        return ResponseEntity.noContent().build();
    }
}
