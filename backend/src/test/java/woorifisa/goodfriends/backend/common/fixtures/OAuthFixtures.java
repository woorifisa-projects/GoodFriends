package woorifisa.goodfriends.backend.common.fixtures;

import woorifisa.goodfriends.backend.auth.dto.OAuthUser;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum OAuthFixtures {

    팬시("팬시", 팬시());
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

    public String getCode() {
        return code;
    }

    public OAuthUser getoAuthUser() {
        return oAuthUser;
    }
}
