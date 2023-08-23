package woorifisa.goodfriends.backend.auth.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import woorifisa.goodfriends.backend.auth.application.OAuthUri;
import woorifisa.goodfriends.backend.auth.dto.response.OAuthUriResponse;

@RequestMapping("/api/auth")
@RestController
public class AuthController {

    private final OAuthUri oAuthUri;

    public AuthController(OAuthUri oAuthUri) {
        this.oAuthUri = oAuthUri;
    }

    @GetMapping("/{oauthProvider}/oauth-uri")
    public ResponseEntity<OAuthUriResponse> generateLink(@PathVariable final String oauthProvider,
                                                         @RequestParam final String redirectUri) {
        OAuthUriResponse oAuthUriResponse = new OAuthUriResponse(oAuthUri.generate(redirectUri));
        return ResponseEntity.ok(oAuthUriResponse);
    }

}
