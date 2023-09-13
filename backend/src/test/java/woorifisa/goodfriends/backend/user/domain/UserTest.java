package woorifisa.goodfriends.backend.user.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import woorifisa.goodfriends.backend.user.exception.InvalidUserException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static woorifisa.goodfriends.backend.common.fixtures.UserFixtures.*;


class UserTest {

    @DisplayName("회원을 생성한다.")
    @Test
    void 회원을_생성한다() {
        // given & when & then
        Assertions.assertDoesNotThrow(() -> new User(팬시_이메일, 팬시_닉네임, 팬시_프로필));
    }

    @DisplayName("회원의 이메일 형식이 맞지 않으면 예외를 던진다.")
    @ParameterizedTest
    @ValueSource(strings = {"toss@", "fancy.gmail.com", "fancy.gmail@", "dev.fancy"})
    void 회원의_이메일_형식이_맞지_않으면_예외를_던진다(final String email) {
        // given & when & then
        assertThatThrownBy(() -> new User(email, 팬시_닉네임, 팬시_프로필))
                .isInstanceOf(InvalidUserException.class);
    }

    @DisplayName("회원의 이름이 1 ~ 20 사이가 아닌 경우 예외를 던진다.")
    @ParameterizedTest
    @ValueSource(strings = {"", "일이삼사오육칠팔구십" + "일이삼사오육칠팔구십일"})
    void 회원의_이름이_1_에서_20_사이가_아닌_경우_예외를_던진다(final String nickname) {
        // given & when & then
        assertThatThrownBy(() -> new User(팬시_이메일, nickname, 팬시_프로필))
                .isInstanceOf(InvalidUserException.class);
    }

}