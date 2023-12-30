package woorifisa.goodfriends.backend.infrastructure.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

// 구글 OAuth 서비스로부터 받은 액세스 토큰 정보를 나타내는 클래스
// 구글 OAuth 서버에서 발급된 액세스 토큰의 속성들을 담고 있으며, 이 속성들은 액세스 토큰 발급 시 반환되는 JSON 응답에서 추출된 데이터를 매핑하기 위한 용도로 사용됩니다.
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GoogleTokenResponse {
    private String accessToken;
    private String refreshToken;
    private String idToken;
    private String expiresIn;
    private String tokenType;
    private String scope;

    protected GoogleTokenResponse() {
    }

    public GoogleTokenResponse(final String accessToken, final String refreshToken, final String idToken,
                               final String expiresIn, final String scope, final String tokenType) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.idToken = idToken;
        this.expiresIn = expiresIn;
        this.scope = scope;
        this.tokenType = tokenType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getIdToken() {
        return idToken;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public String getTokenType() {
        return tokenType;
    }

    public String getScope() {
        return scope;
    }
}
