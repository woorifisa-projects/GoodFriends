package woorifisa.goodfriends.backend.common.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import woorifisa.goodfriends.backend.auth.application.AuthService;
import woorifisa.goodfriends.backend.auth.application.TokenProvider;
import woorifisa.goodfriends.backend.common.config.ExternalApiConfig;

@SpringBootTest(classes = ExternalApiConfig.class)
public abstract class ServiceTest {

    @Autowired
    private AuthService authService;

    @Autowired
    private TokenProvider tokenProvider;

}
