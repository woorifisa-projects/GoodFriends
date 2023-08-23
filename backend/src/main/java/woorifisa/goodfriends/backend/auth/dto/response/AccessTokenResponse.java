package woorifisa.goodfriends.backend.auth.dto.response;

// 클라이언트에게 반환되는 응답 객체(클라이언트 애플리케이션과의 통신을 위한 응답 객체)
// 사용자가 로그인이나 인증을 성공적으로 마치고 클라이언트에게 액세스 토큰과 관련된 정보를 반환하기 위해 사용
public class AccessTokenResponse {

    private Long id;

    private String accessToken;

    public AccessTokenResponse() {
    }

    public AccessTokenResponse(Long id, String accessToken) {
        this.id = id;
        this.accessToken = accessToken;
    }

    public Long getId() {
        return id;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
