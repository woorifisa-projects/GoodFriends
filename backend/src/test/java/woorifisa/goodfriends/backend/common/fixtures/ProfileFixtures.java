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
    public static final String 모바일_번호 = "010-1234-5678";

    public static final String 주소 = "서울특별시 강남구";

    public static final AccountType TOSS = AccountType.TOSS;

    public static final String TOSS_ACCOUNT_NUMBER = "112233445566778899";

    public static Profile 팬시() {
        return new Profile(new User(팬시_이메일, 팬시_닉네임, 팬시_프로필), 모바일_번호, 주소, TOSS, TOSS_ACCOUNT_NUMBER);
    }

}
