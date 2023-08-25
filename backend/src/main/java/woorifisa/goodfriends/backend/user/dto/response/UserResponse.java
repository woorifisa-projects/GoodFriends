package woorifisa.goodfriends.backend.user.dto.response;

import woorifisa.goodfriends.backend.user.domain.User;

public class UserResponse {
    private Long id;
    private String email;
    private String nickname;
    private String profileImageUrl;

    public UserResponse(Long id, String email, String nickname, String profileImageUrl) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
    }
    public UserResponse(final User user) {
        this(user.getId(), user.getEmail(),user.getNickname(), user.getProfileImageUrl());
    }

    public Long getId() {
        return id;
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
}
