package woorifisa.goodfriends.backend.common.fixtures;

import woorifisa.goodfriends.backend.profile.domain.AccountType;
import woorifisa.goodfriends.backend.profile.domain.Profile;

import static woorifisa.goodfriends.backend.common.fixtures.ProductFixtures.*;

public class ProfileFixtures {

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

    public static Profile 프로필1() {
        return Profile.builder()
                .user(상품1().getUser())
                .address(주소1)
                .mobileNumber(휴대전화1)
                .accountNumber(계좌번호1)
                .accountType(AccountType.HANA)
                .build();
    }

    public static Profile 프로필2() {
        return Profile.builder()
                .user(상품2().getUser())
                .address(주소2)
                .mobileNumber(휴대전화2)
                .accountNumber(계좌번호2)
                .accountType(AccountType.WOORI)
                .build();
    }

    public static Profile 프로필3() {
        return Profile.builder()
                .user(상품3().getUser())
                .address(주소3)
                .mobileNumber(휴대전화3)
                .accountNumber(계좌번호3)
                .accountType(AccountType.SHINHAN)
                .build();
    }
}
