package woorifisa.goodfriends.backend.user.dto.response;

import lombok.Builder;
import lombok.Getter;
import woorifisa.goodfriends.backend.user.domain.User;

@Getter
public class OrderWithUserResponse {

    private String email;
    private String nickName;
    private String profileImageUrl;


    @Builder
    public OrderWithUserResponse(final String email, final String nickName, final String profileImageUrl) {
        this.email = email;
        this.nickName = nickName;
        this.profileImageUrl = profileImageUrl;
    }

    public static OrderWithUserResponse of(final User user) {
        return OrderWithUserResponse.builder()
                .email(user.getEmail())
                .nickName(user.getNickname())
                .profileImageUrl(user.getProfileImageUrl())
                .build();
    }
}
