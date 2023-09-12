package woorifisa.goodfriends.backend.auth.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import woorifisa.goodfriends.backend.user.domain.User;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static woorifisa.goodfriends.backend.common.fixtures.UserFixtures.팬시;

class OAuthTokenTest {

    @DisplayName("OAuth token을 생성한다")
    @Test
    void OAuth_token을_생성한다() {
        // given
        User 팬시 = 팬시();
        String refreshToken = "aaaaabbbbbccccc";

        // when & then
        assertDoesNotThrow(() -> new OAuthToken(팬시, refreshToken));
    }

    @DisplayName("refresh token을 교체한다.")
    @Test
    void 새로운_refresh_token으로_교체한다() {
        // given
        User 팬시 = 팬시();
        String refreshToken = "asdasdasdasdasdas";
        OAuthToken oAuthToken = new OAuthToken(팬시, refreshToken);

        String updateRefreshToken = "asdkljalsjxcvlxvijn";

        // when
        oAuthToken.change(updateRefreshToken);

        // then
        assertThat(oAuthToken.getRefreshToken()).isEqualTo(updateRefreshToken);
    }

}