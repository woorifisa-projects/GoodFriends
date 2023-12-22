package woorifisa.goodfriends.backend.profile.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AccountType {
    KOOKMIN("국민은행"),
    SHINHAN("신한은행"),
    HANA("하나은행"),
    WOORI("우리은행"),
    NH_NONGHYUP("농협은행"),
    KAKAO("카카오뱅크"),
    TOSS("토스뱅크");
    private final String text;
}
