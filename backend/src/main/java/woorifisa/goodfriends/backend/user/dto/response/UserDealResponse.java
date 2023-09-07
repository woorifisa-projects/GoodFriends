package woorifisa.goodfriends.backend.user.dto.response;

import lombok.Getter;

@Getter
public class UserDealResponse {

    private String nickName;

    private String profileImageUrl;

    private String email;

    public UserDealResponse() {
    }

    public UserDealResponse(String nickName, String profileImageUrl, String email) {
        this.nickName = nickName;
        this.profileImageUrl = profileImageUrl;
        this.email = email;
    }
}
