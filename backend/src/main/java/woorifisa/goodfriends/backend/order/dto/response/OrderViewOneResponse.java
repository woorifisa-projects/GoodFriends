package woorifisa.goodfriends.backend.order.dto.response;

import lombok.Getter;

@Getter
public class OrderViewOneResponse {

    private Long id;

    private Long userId;

    private String profileImageUrl;

    private String nickName;

    private String possibleDate;

    private String possibleTime;

    private String requirements;

    public OrderViewOneResponse() {
    }

    public OrderViewOneResponse(Long id, Long userId, String profileImageUrl, String nickName, String possibleDate, String possibleTime, String requirements) {
        this.id = id;
        this.userId = userId;
        this.profileImageUrl = profileImageUrl;
        this.nickName = nickName;
        this.possibleDate = possibleDate;
        this.possibleTime = possibleTime;
        this.requirements = requirements;
    }
}
