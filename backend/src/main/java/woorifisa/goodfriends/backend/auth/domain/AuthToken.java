package woorifisa.goodfriends.backend.auth.domain;

import woorifisa.goodfriends.backend.auth.exception.NotFoundTokenException;

// 인증 토큰에 대한 정보를 담고 있는 클래스(보안 및 인증 관련 정보를 다루기 위한 내부적인 클래스)
// 사용자 인증 및 권한 부여를 위해 사용되며, 사용자의 ID와 접근 토큰(access token) 등의 정보를 포함
public class AuthToken {

    private Long id;

    private String accessToken;

    public AuthToken(Long id, String accessToken) {
        this.id = id;
        this.accessToken = accessToken;
    }

    public Long getId() {
        return id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void validateHasSameRefreshToken(String refreshTokenForRenew, String otherRefreshToken) {
        if (!refreshTokenForRenew.equals(otherRefreshToken)) {
            throw new NotFoundTokenException("회원의 리프레시 토큰이 아닙니다.");
        }
    }
    public void validateHasSameAccessToken(String accessToken, String otherAccessToken) {
        if(!accessToken.equals(otherAccessToken)) {
            throw new NotFoundTokenException("회원의 액세스 토큰이 아닙니다.");
        }
    }
}
