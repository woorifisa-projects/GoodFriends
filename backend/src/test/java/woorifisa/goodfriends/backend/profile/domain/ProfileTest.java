package woorifisa.goodfriends.backend.profile.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import woorifisa.goodfriends.backend.user.domain.User;

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
        Assertions.assertDoesNotThrow(() -> new Profile(new User(팬시_이메일, 팬시_닉네임, 팬시_프로필), 팬시_모바일_번호, 팬시_주소, 팬시_계좌종류, 팬시_계좌번호));
    }

}