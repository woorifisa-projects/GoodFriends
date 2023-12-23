package woorifisa.goodfriends.backend.profile.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import woorifisa.goodfriends.backend.user.domain.User;
import woorifisa.goodfriends.backend.user.exception.InvalidUserException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static woorifisa.goodfriends.backend.common.fixtures.UserFixtures.팬시_이메일;
import static woorifisa.goodfriends.backend.common.fixtures.UserFixtures.팬시_닉네임;
import static woorifisa.goodfriends.backend.common.fixtures.UserFixtures.팬시_프로필;
import static woorifisa.goodfriends.backend.common.fixtures.ProfileFixtures.팬시_모바일_번호;
import static woorifisa.goodfriends.backend.common.fixtures.ProfileFixtures.팬시_주소;
import static woorifisa.goodfriends.backend.common.fixtures.ProfileFixtures.팬시_계좌종류;
import static woorifisa.goodfriends.backend.common.fixtures.ProfileFixtures.팬시_계좌번호;

class ProfileTest {

    @DisplayName("프로필을 생성한다.")
    @Test
    void 프로필을_생성한다() {
        // given & when & then
        assertDoesNotThrow(() -> new Profile(new User(팬시_이메일, 팬시_닉네임, 팬시_프로필), 팬시_모바일_번호, 팬시_주소, 팬시_계좌종류, 팬시_계좌번호));
    }

    @DisplayName("회원의 핸드폰 번호 형식이 맞지 않으면 예외를 던진다.")
    @ParameterizedTest
    @ValueSource(strings = {"0000-0000-0000", "1-2-3", "123-456-789"})
    void 회원의_핸드폰_번호_형식이_맞지_않으면_예외를_던진다(final String mobileNumber) {
        // given & when & then
        assertThatThrownBy(() -> new Profile(new User(팬시_이메일, 팬시_닉네임, 팬시_프로필), mobileNumber, 팬시_주소, 팬시_계좌종류, 팬시_계좌번호))
                .isInstanceOf(InvalidUserException.class);

     }

    @DisplayName("회원의 핸드폰 번호 형식이 맞으면 성공한다.")
    @ParameterizedTest
    @ValueSource(strings = {"010-1234-5678", "011-2468-1357", "010-1111-2222"})
    void 회원의_핸드폰_번호_형식이_맞으면_성공한다(final String mobileNumber) {
        // given & when & then
        assertThatCode(() -> new Profile(new User(팬시_이메일, 팬시_닉네임, 팬시_프로필), mobileNumber, 팬시_주소, 팬시_계좌종류, 팬시_계좌번호))
                .doesNotThrowAnyException();
    }
}