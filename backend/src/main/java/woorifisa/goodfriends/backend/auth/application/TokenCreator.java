package woorifisa.goodfriends.backend.auth.application;

import woorifisa.goodfriends.backend.auth.domain.AuthToken;

// 토큰을 생성하는 기능을 추상화한 클래스
public interface TokenCreator {
    AuthToken createAuthToken(final Long memberId);

    Long extractPayLoad(String accessToken);
}
