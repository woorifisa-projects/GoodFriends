package woorifisa.goodfriends.backend.auth.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import woorifisa.goodfriends.backend.auth.domain.AuthToken;
import woorifisa.goodfriends.backend.auth.domain.TokenRepository;

//  TokenCreator 인터페이스를 구현한 구체적인 클래스
// TokenProvider 인터페이스를 사용하여 실제 인증 토큰을 생성
@Component
public class AuthTokenCreator implements TokenCreator {

    private final TokenProvider tokenProvider;
    private final TokenRepository tokenRepository;
    private final AuthTokenResponseHandler authTokenResponseHandler;

    public AuthTokenCreator(final TokenProvider tokenProvider, final TokenRepository tokenRepository,
                            final AuthTokenResponseHandler authTokenResponseHandler) {
        this.tokenProvider = tokenProvider;
        this.tokenRepository = tokenRepository;
        this.authTokenResponseHandler = authTokenResponseHandler;
    }

    @Override
    public AuthToken createAuthToken(final Long userId) {
        Long id = userId;
        String accessToken = tokenProvider.createAccessToken(String.valueOf(userId));
        String refreshToken = createRefreshToken(userId);

        // 클라이언트에게 리프레시 토큰 값을 Set-Cookie 로 전달
        authTokenResponseHandler.setRefreshTokenCookie(refreshToken);
        return new AuthToken(id, accessToken);
    }

    public AuthToken createAdminToken(final Long adminId) {
        Long id = adminId;
        String accessToken = tokenProvider.createAdminAccessToken(String.valueOf(adminId));
        return new AuthToken(id, accessToken);
    }

    private String createRefreshToken(final Long userId) {
        if(tokenRepository.exist(userId)) {
            return tokenRepository.getToken(userId);
        }
        String refreshToken = tokenProvider.createRefreshToken(String.valueOf(userId));
        return tokenRepository.save(userId, refreshToken);
    }
    @Override
    public Long extractPayLoad(final String accessToken) {
        tokenProvider.validateToken(accessToken);
        return Long.valueOf(tokenProvider.getPayload(accessToken));
    }

    public AuthToken renewAuthToken(final String refreshToken) {
        tokenProvider.validateToken(refreshToken);
        Long memberId = Long.valueOf(tokenProvider.getPayload(refreshToken));

        String accessTokenForRenew = tokenProvider.createAccessToken(String.valueOf(memberId));
        String refreshTokenForRenew = tokenRepository.getToken(memberId);

        // 클라이언트로 리프레시 토큰 값을 전달하는 부분
        authTokenResponseHandler.setRefreshTokenCookie(refreshTokenForRenew);

        AuthToken renewalAuthAccessToken = new AuthToken(memberId, accessTokenForRenew);
        renewalAuthAccessToken.validateHasSameRefreshToken(refreshTokenForRenew, refreshToken);
        return renewalAuthAccessToken;
    }
}
