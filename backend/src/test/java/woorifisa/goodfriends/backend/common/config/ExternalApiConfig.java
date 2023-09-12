package woorifisa.goodfriends.backend.common.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import woorifisa.goodfriends.backend.auth.application.OAuthClient;
import woorifisa.goodfriends.backend.infrastructure.oauth.client.StubOAuthClient;

@TestConfiguration
public class ExternalApiConfig {

    @Bean
    public OAuthClient oAuthClient() {
        return new StubOAuthClient();
    }
}
