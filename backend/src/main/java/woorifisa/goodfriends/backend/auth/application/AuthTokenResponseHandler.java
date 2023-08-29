package woorifisa.goodfriends.backend.auth.application;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthTokenResponseHandler {

    private final HttpServletResponse httpServletResponse;

    @Autowired
    public AuthTokenResponseHandler(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    public void setRefreshTokenCookie(String refreshToken) {
        Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
        // HttpOnly 설정시 클라이언트에서 쿠키에 직접 js 코드로 접근이 불가능하고
        // 이는 refreshToken 유효성 검사시 서버의 네트워크 통신을 한번 더 요구하고 추가 API 작성 및 클라이언트 OAuth 로직에서 복잡성을 유발하여
        // 현 기준 HttpOnly 옵션은 일단 사용을 안하는 것으로 결정
        refreshTokenCookie.setHttpOnly(false);
        refreshTokenCookie.setSecure(true); // https 옵션 설정
        refreshTokenCookie.setMaxAge(14 * 24 * 60 * 60); // 리프레시 토큰 유효 기간 설정 (14일)
        refreshTokenCookie.setPath("/"); // 쿠키의 유효 경로 설정 (애플리케이션 전체)
        httpServletResponse.addCookie(refreshTokenCookie);

    }
}
