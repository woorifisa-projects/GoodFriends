package woorifisa.goodfriends.backend.infrastructure.oauth.uri;

import org.springframework.stereotype.Component;
import woorifisa.goodfriends.backend.auth.application.OAuthUri;
import woorifisa.goodfriends.backend.global.config.properties.GoogleProperties;

@Component
public class GoogleOAuthUri implements OAuthUri {

    private final GoogleProperties properties;

    public GoogleOAuthUri(final GoogleProperties properties) {
        this.properties = properties;
    }

    @Override
    public String generate(final String redirectUri) {
        return properties.getOAuthEndPoint() + "?"
                + "client_id=" + properties.getClientId() + "&"
                + "redirect_uri=" + redirectUri + "&"
                + "response_type=code&"
                + "scope=" + String.join(" ", properties.getScopes()) + "&"
                + "access_type=" + properties.getAccessType() + "&"
                + "prompt=consent"; // 추가된 부분
    }

}
