package woorifisa.goodfriends.backend.common.fixtures;

import woorifisa.goodfriends.backend.profile.domain.AccountType;
import woorifisa.goodfriends.backend.profile.domain.Profile;
import woorifisa.goodfriends.backend.user.domain.User;

import javax.persistence.Column;

import static woorifisa.goodfriends.backend.common.fixtures.UserFixtures.팬시_닉네임;
import static woorifisa.goodfriends.backend.common.fixtures.UserFixtures.팬시_이메일;
import static woorifisa.goodfriends.backend.common.fixtures.UserFixtures.팬시_프로필;

public class ProfileFixtures {

    /* 팬시 */
    public static final String 팬시_모바일_번호 = "010-1234-5678";

    public static final String 팬시_주소 = "서울특별시 강남구";

    public static final AccountType 팬시_계좌종류 = AccountType.TOSS;

    public static final String 팬시_계좌번호 = "112233445566778899";

    public static Profile 팬시() {
        return new Profile(new User(팬시_이메일, 팬시_닉네임, 팬시_프로필), 팬시_모바일_번호, 팬시_주소, 팬시_계좌종류, 팬시_계좌번호);
    }

}
