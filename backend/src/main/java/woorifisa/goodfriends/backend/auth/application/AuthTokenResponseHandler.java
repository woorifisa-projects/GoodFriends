package woorifisa.goodfriends.backend.auth.application;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

@Component
public class AuthTokenResponseHandler {

    private final HttpServletResponse httpServletResponse;

    @Autowired
    public AuthTokenResponseHandler(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    public void setRefreshTokenCookie(String refreshToken) {
        ResponseCookie refreshTokenCookie = ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true)
                .secure(true)
                .sameSite("None") // SameSite 설정을 None으로 추가
                .maxAge(14 * 24 * 60 * 60) // 리프레시 토큰 유효 기간 설정 (14일)
                .path("/") // 쿠키의 유효 경로 설정 (애플리케이션 전체)
                .build();

        httpServletResponse.addHeader("Set-Cookie", refreshTokenCookie.toString());
    }

    public void setRefreshTokenDeleteCookie(String refreshToken){
        ResponseCookie refreshTokenCookie = ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true)
                .secure(true)
                .sameSite("None") // SameSite 설정을 None으로 추가
                .maxAge(0)
                .path("/")
                .build();

        httpServletResponse.addHeader("Set-Cookie", refreshTokenCookie.toString());
    }
}