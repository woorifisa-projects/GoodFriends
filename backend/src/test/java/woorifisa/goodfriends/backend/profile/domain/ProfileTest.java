package woorifisa.goodfriends.backend.profile.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import woorifisa.goodfriends.backend.user.domain.User;

import static woorifisa.goodfriends.backend.common.fixtures.UserFixtures.팬시_이메일;
import static woorifisa.goodfriends.backend.common.fixtures.UserFixtures.팬시_닉네임;
import static woorifisa.goodfriends.backend.common.fixtures.UserFixtures.팬시_프로필;
import static woorifisa.goodfriends.backend.common.fixtures.ProfileFixtures.모바일_번호;
import static woorifisa.goodfriends.backend.common.fixtures.ProfileFixtures.주소;
import static woorifisa.goodfriends.backend.common.fixtures.ProfileFixtures.TOSS;
import static woorifisa.goodfriends.backend.common.fixtures.ProfileFixtures.TOSS_ACCOUNT_NUMBER;

class ProfileTest {

    @DisplayName("프로필을 생성한다.")
    @Test
    void 프로필을_생성한다() {
        // given & when & then
        Assertions.assertDoesNotThrow(() -> new Profile(new User(팬시_이메일, 팬시_닉네임, 팬시_프로필), 모바일_번호, 주소, TOSS, TOSS_ACCOUNT_NUMBER));
    }

}