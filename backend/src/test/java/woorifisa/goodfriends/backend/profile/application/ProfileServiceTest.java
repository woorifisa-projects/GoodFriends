package woorifisa.goodfriends.backend.profile.application;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import woorifisa.goodfriends.backend.common.annotation.ServiceTest;
import woorifisa.goodfriends.backend.profile.dto.request.ProfileUpdateRequest;
import woorifisa.goodfriends.backend.user.domain.User;
import woorifisa.goodfriends.backend.user.domain.UserRepository;

import static woorifisa.goodfriends.backend.common.fixtures.UserFixtures.팬시_이메일;
import static woorifisa.goodfriends.backend.common.fixtures.UserFixtures.팬시_닉네임;
import static woorifisa.goodfriends.backend.common.fixtures.ProfileFixtures.팬시_모바일_번호;
import static woorifisa.goodfriends.backend.common.fixtures.ProfileFixtures.팬시_주소;
import static woorifisa.goodfriends.backend.common.fixtures.ProfileFixtures.팬시_계좌종류;
import static woorifisa.goodfriends.backend.common.fixtures.ProfileFixtures.팬시_계좌번호;

class ProfileServiceTest extends ServiceTest {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private UserRepository userRepository;

    // @DisplayName("프로필 정보(닉네임, 핸드폰 번호, 주소, 계좌 종류, 계좌 번호)를 수정한다")
    // @Test
    // void 프로필_정보를_수정한다() {

    //     Long userId = 10L; // DB에 있는 userId 값
    //     ProfileUpdateRequest profileUpdateRequest = new ProfileUpdateRequest(팬시_이메일, 팬시_닉네임, 팬시_모바일_번호, 팬시_주소,팬시_계좌종류, 팬시_계좌번호);

    //     profileService.update(userId, profileUpdateRequest);

    //     User actual = userRepository.getById(10L);
    //     Assertions.assertThat(actual.getNickname()).isEqualTo("팬시");
    // }
}
