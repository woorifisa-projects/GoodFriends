package woorifisa.goodfriends.backend.auth.application;

import org.springframework.stereotype.Component;
import woorifisa.goodfriends.backend.auth.domain.AuthToken;

//  TokenCreator 인터페이스를 구현한 구체적인 클래스
// TokenProvider 인터페이스를 사용하여 실제 인증 토큰을 생성
@Component
public class AuthTokenCreator implements TokenCreator {

    private final TokenProvider tokenProvider;

    public AuthTokenCreator(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    public AuthToken createAuthToken(final Long userId) {
        Long id = userId;
        String accessToken = tokenProvider.createAccessToken(String.valueOf(userId));

        return new AuthToken(id, accessToken);
    }

    @Override
    public Long extractPayLoad(String accessToken) {
        tokenProvider.validateToken(accessToken);
        return Long.valueOf(tokenProvider.getPayload(accessToken));
    }
}
