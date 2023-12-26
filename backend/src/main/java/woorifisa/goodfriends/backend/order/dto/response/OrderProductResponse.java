package woorifisa.goodfriends.backend.order.dto.response;

import lombok.Builder;
import lombok.Getter;
import woorifisa.goodfriends.backend.order.domain.Order;

@Getter
public class OrderProductResponse {

    private Long id;

    private Long userId;

    private String profileImageUrl;

    private String nickName;

    private String possibleDate;

    private String possibleTime;

    private String requirements;

    @Builder
    public OrderProductResponse(final Long id, final Long userId, final String profileImageUrl, final String nickName,
                                final String possibleDate, final String possibleTime, final String requirements) {
        this.id = id;
        this.userId = userId;
        this.profileImageUrl = profileImageUrl;
        this.nickName = nickName;
        this.possibleDate = possibleDate;
        this.possibleTime = possibleTime;
        this.requirements = requirements;
    }

    public static OrderProductResponse of(final Order order) {
        return OrderProductResponse.builder()
                .id(order.getId())
                .userId(order.getUser().getId())
                .profileImageUrl(order.getUser().getProfileImageUrl())
                .nickName(order.getUser().getNickname())
                .possibleDate(order.getPossibleDate())
                .possibleTime(order.getPossibleTime())
                .requirements(order.getRequirements())
                .build();
    }
}
