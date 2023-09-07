package woorifisa.goodfriends.backend.admin.dto.request;

import lombok.Builder;


public class UserUpdateRequest {

    private final String nickname;
    private final String mobilePhone;
    private final String address;
    private final boolean activated;

    private final int banCount;

    public UserUpdateRequest(String nickname, String mobilePhone,String address,boolean activated, int banCount) {
        this.nickname = nickname;
        this.mobilePhone = mobilePhone;
        this.address = address;
        this.activated = activated;
        this.banCount = banCount;
    }

    public String getNickname() {
        return nickname;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getAddress() {
        return address;
    }

    public boolean isActivated() {
        return activated;
    }

    public int getBanCount() {
        return banCount;
    }
}
