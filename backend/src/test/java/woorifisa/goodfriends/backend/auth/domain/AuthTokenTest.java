package woorifisa.goodfriends.backend.auth.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import woorifisa.goodfriends.backend.auth.exception.NotFoundTokenException;


public class AuthTokenTest {

    @DisplayName("같은 액세스 토큰 값이면 정상적으로 메서드를 종료한다.")
    @Test
    void 같은_액세스_토큰_값이면_정상적으로_메서드를_종료한다() {
        //given
        AuthToken authToken = new AuthToken(1L, "dummyAccessTokenValue");
        String accessToken = "dummyAccessTokenValue";

        // when & then
        authToken.validateHasSameAccessToken(accessToken, authToken.getAccessToken());
    }

    @DisplayName("같은 액세스 토큰 값이 아니면 예외를 발생한다.")
    @Test
    void 같은_액세스_토큰_값이_아니면_예외를_발생한다() {
        // given
        AuthToken authToken = new AuthToken(1L, "dummyAccessTokenValue");

        // when & then
        Assertions.assertThatThrownBy(() -> authToken.validateHasSameAccessToken("dummyAccessTokenValue", "InvalidAccessToken"))
                .isInstanceOf(NotFoundTokenException.class);
    }
}
