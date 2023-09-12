package woorifisa.goodfriends.backend.auth.application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.event.ApplicationEvents;
import org.springframework.test.context.event.RecordApplicationEvents;
import woorifisa.goodfriends.backend.auth.dto.request.TokenRenewalRequest;
import woorifisa.goodfriends.backend.auth.dto.response.AccessTokenResponse;
import woorifisa.goodfriends.backend.auth.event.UserSavedEvent;
import woorifisa.goodfriends.backend.common.annotation.ServiceTest;
import woorifisa.goodfriends.backend.user.domain.User;
import woorifisa.goodfriends.backend.user.domain.UserRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static woorifisa.goodfriends.backend.common.fixtures.OAuthFixtures.USER;

@RecordApplicationEvents
public class AuthServiceTest extends ServiceTest {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplicationEvents events;

    @DisplayName("토큰 생성하면 OAuth 서버에서 인증 후 토큰들을 반환한다.") // 한번만 Pass되면서 DB에 저장됨
    @Test
    void 토큰_생성을_하면_OAuth_서버에서_인증후_토큰들을_반환한다() {
        // given & when
        AccessTokenResponse actual = authService.generateAccessAndRefreshToken(USER.getoAuthUser());

        // then
        Assertions.assertAll(() -> {
            assertThat(actual.getAccessToken()).isNotEmpty();
            assertThat(events.stream(UserSavedEvent.class).count()).isEqualTo(1);
        });
    }
    @DisplayName("이미 가입된 회원이고 저장된 RefreshToken이 있으면, 저장된 RefreshToken을 반환한다.")
    @Test
    void 이미_가입된_회원이고_저장된_RefreshToken이_있으면_저장된_RefreshToken을_반환한다() {
        // 이미 가입된 회원이 소셜 로그인 버튼을 클릭했을 경우엔 회원가입 과정이 생략되고, 곧바로 access token과 refreshtoken이 발급되어야 한다.

        // given
        AccessTokenResponse response = authService.generateAccessAndRefreshToken(USER.getoAuthUser());

        // when
        AccessTokenResponse actual = authService.generateAccessAndRefreshToken(USER.getoAuthUser());

        // then
        assertThat(actual.getAccessToken()).isEqualTo(response.getAccessToken());
    }
}
