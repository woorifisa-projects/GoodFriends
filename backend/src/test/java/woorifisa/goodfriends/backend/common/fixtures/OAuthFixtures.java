package woorifisa.goodfriends.backend.common.fixtures;

import woorifisa.goodfriends.backend.auth.dto.OAuthUser;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum OAuthFixtures {

    팬시("팬시", 팬시()),

    USER("user authorization code", USER());
    private String code;
    private OAuthUser oAuthUser;

    OAuthFixtures(String code, OAuthUser oAuthUser) {
        this.code = code;
        this.oAuthUser = oAuthUser;
    }
    public static OAuthUser parseOAuthUser(final String code) {
        OAuthFixtures oAuthFixtures = Arrays.stream(values())
                .filter(value -> value.code.equals(code))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
        return oAuthFixtures.oAuthUser;
    }

    private static OAuthUser 팬시() {
        String 팬시_이메일 = "fancy@email.com";
        String 팬시_이름 = "팬시";
        String 팬시_프로필 = "/fancy.png";
        String 팬시_REFRESH_TOKEN = "fancy_refresh_token_value";
        return new OAuthUser(팬시_이메일, 팬시_이름, 팬시_프로필, 팬시_REFRESH_TOKEN);
    }

    private static OAuthUser USER() {
        String USER_이메일 = "user@email.com";
        String USER_이름 = "user";
        String USER_프로필 = "/user.png";
        String USER_REFRESH_TOKEN = "user.refresh.token";
        return new OAuthUser(USER_이메일, USER_이름, USER_프로필, USER_REFRESH_TOKEN);
    }

    public String getCode() {
        return code;
    }

    public OAuthUser getoAuthUser() {
        return oAuthUser;
    }
}
