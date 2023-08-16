package woorifisa.goodfriends.backend.auth.controller;

import org.springframework.web.bind.annotation.*;
import woorifisa.goodfriends.backend.auth.service.OAuthLoginService;

@RestController
@RequestMapping(value = "/login/oauth2", produces = "application/json")
public class OAuthLoginController {

    OAuthLoginService oauthloginService;

    public OAuthLoginController(OAuthLoginService oauthloginService) {
        this.oauthloginService = oauthloginService;
    }

    @GetMapping("/code/{registrationId}")
    public void googleLogin(@RequestParam String code, @PathVariable String registrationId) {
        oauthloginService.socialLogin(code, registrationId);
    }
}