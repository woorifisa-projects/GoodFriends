package woorifisa.goodfriends.backend.auth.application;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import woorifisa.goodfriends.backend.auth.exception.InvalidTokenException;

import static org.junit.jupiter.api.Assertions.*;

class JwtTokenProviderTest {

    private static final String JWT_SECRET_KEY = "A".repeat(32); // Secret Key는 최소 32바이트 이상이어야함.
    public static final int JWT_ACCESS_TOKEN_EXPIRE_LENGTH = 3600000;
    public static final int JWT_REFRESH_TOKEN_EXPIRE_LENGTH = 1210000000;
    public static final String PAYLOAD = "payload";

    private final JwtTokenProvider jwtTokenProvider = new JwtTokenProvider(JWT_SECRET_KEY, JWT_ACCESS_TOKEN_EXPIRE_LENGTH, JWT_REFRESH_TOKEN_EXPIRE_LENGTH);

    @DisplayName("액세스 토큰을 생성한다")
    @Test
    void 액세스_토큰을_생성한다() {
        // given & when
        String actual = jwtTokenProvider.createAccessToken(PAYLOAD);

        // then
        Assertions.assertThat(actual.split("\\.")).hasSize(3);
    }

    @DisplayName("리프레시 토큰을 생성한다")
    @Test
    void 리프레시_토큰을_생성한다() {
        // given & when
        String actual = jwtTokenProvider.createRefreshToken(PAYLOAD);

        // then
        Assertions.assertThat(actual.split("\\.")).hasSize(3);
    }

    @DisplayName("액세스 토큰을 검증하여 토큰이 만료된 경우 예외를 던진다.")
    @Test
    void 액세스_토큰을_검증하여_만료된_경우_예외를_던진다() {
        // given
        TokenProvider expiredJwtTokenProvider = new JwtTokenProvider(JWT_SECRET_KEY, 0, 0);
        String expiredToken = expiredJwtTokenProvider.createAccessToken(PAYLOAD);

        // when & then
        Assertions.assertThatThrownBy(() -> jwtTokenProvider.validateToken(expiredToken))
                .isInstanceOf(InvalidTokenException.class);
    }
}