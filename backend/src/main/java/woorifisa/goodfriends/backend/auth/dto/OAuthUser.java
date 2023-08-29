package woorifisa.goodfriends.backend.auth.dto;

import woorifisa.goodfriends.backend.user.domain.User;

public class OAuthUser {

    private final String email;
    private final String nickname;
    private final String profileImageUrl;
    private final String refreshToken;

    public OAuthUser(String email, String nickname, String profileImageUrl, String refreshToken) {
        this.email = email;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.refreshToken = refreshToken;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
    public User toUser() {
        return new User(email, nickname, profileImageUrl);
    }
}
