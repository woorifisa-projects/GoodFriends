package woorifisa.goodfriends.backend.profile.dto.response;

import lombok.Builder;
import lombok.Getter;
import woorifisa.goodfriends.backend.order.domain.OrderStatus;

@Getter
public class ProfilePurchaseResponse {
    private final Long productId;
    private final String title;
    private final OrderStatus status;
    private final int sellPrice;
    private final String imageUrl;

    @Builder
    public ProfilePurchaseResponse(final Long productId, final String title, final OrderStatus status, final int sellPrice, final String imageUrl) {
        this.productId = productId;
        this.title = title;
        this.status = status;
        this.sellPrice = sellPrice;
        this.imageUrl = imageUrl;
    }
}
