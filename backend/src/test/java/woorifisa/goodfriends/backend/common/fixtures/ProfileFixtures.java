package woorifisa.goodfriends.backend.common.fixtures;

import woorifisa.goodfriends.backend.profile.domain.AccountType;
import woorifisa.goodfriends.backend.profile.domain.Profile;
import woorifisa.goodfriends.backend.user.domain.User;

import javax.persistence.Column;
import static woorifisa.goodfriends.backend.common.fixtures.ProductFixtures.*;
import static woorifisa.goodfriends.backend.common.fixtures.UserFixtures.*;
import static woorifisa.goodfriends.backend.common.fixtures.UserFixtures.팬시_닉네임;
import static woorifisa.goodfriends.backend.common.fixtures.UserFixtures.팬시_이메일;
import static woorifisa.goodfriends.backend.common.fixtures.UserFixtures.팬시_프로필;

public class ProfileFixtures {

    /* 팬시 */
    public static final String 팬시_모바일_번호 = "010-1234-5678";

    public static final String 팬시_주소 = "서울특별시 강남구";

    public static final AccountType 팬시_계좌종류 = AccountType.TOSS;

    public static final String 팬시_계좌번호 = "112233445566778899";

    // 주소
    public static final String 주소1 = "망원동";
    public static final String 주소2 = "상암동";
    public static final String 주소3 = "잠원동";

    // 휴대전화
    public static final String 휴대전화1 = "010-1111-2222";
    public static final String 휴대전화2 = "010-3333-4444";
    public static final String 휴대전화3 = "010-5555-6666";

    // 계좌번호
    public static final String 계좌번호1 = "11111111";
    public static final String 계좌번호2 = "22222222";
    public static final String 계좌번호3 = "33333333";

    public static Profile 팬시() {
        return new Profile(new User(팬시_이메일, 팬시_닉네임, 팬시_프로필), 팬시_모바일_번호, 팬시_주소, 팬시_계좌종류, 팬시_계좌번호);
    }
    public static Profile 프로필1() {
        return Profile.builder()
                .user(코코())
                .address(주소1)
                .mobileNumber(휴대전화1)
                .accountNumber(계좌번호1)
                .accountType(AccountType.HANA)
                .build();
    }

    public static Profile 프로필2() {
        return Profile.builder()
                .user(춘식())
                .address(주소2)
                .mobileNumber(휴대전화2)
                .accountNumber(계좌번호2)
                .accountType(AccountType.WOORI)
                .build();
    }

    public static Profile 프로필3() {
        return Profile.builder()
                .user(고잉홈())
                .address(주소3)
                .mobileNumber(휴대전화3)
                .accountNumber(계좌번호3)
                .accountType(AccountType.SHINHAN)
                .build();
    }
}
