package woorifisa.goodfriends.backend.auth.dto;

import woorifisa.goodfriends.backend.user.domain.User;

public class OAuthMember {

    private final String email;
    private final String nickname;
    private final String profileImageUrl;

    public OAuthMember(String email, String nickname, String profileImageUrl) {
        this.email = email;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
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

    public User toUser() {
        return new User(email, nickname, profileImageUrl);
    }
}
