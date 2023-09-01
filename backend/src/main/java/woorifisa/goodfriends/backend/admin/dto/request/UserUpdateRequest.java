package woorifisa.goodfriends.backend.admin.dto.request;

import lombok.Builder;


public class UserUpdateRequest {

    private String nickname;

    private boolean activated;

    private int banCount;

    public UserUpdateRequest(String nickname, boolean activated, int banCount) {
        this.nickname = nickname;
        this.activated = activated;
        this.banCount = banCount;
    }

    public String getNickname() {
        return nickname;
    }

    public boolean getActivated() {
        return activated;
    }

    public int getBanCount() {
        return banCount;
    }
}
