package woorifisa.goodfriends.backend.profile.dto.response;

import lombok.Getter;
import woorifisa.goodfriends.backend.order.domain.OrderStatus;

@Getter
public class ProfilePurchaseResponse {
    private final Long productId;

    private final String title;

    private final OrderStatus status;

    private final int sellPrice;

    private final String imageUrl;

    public ProfilePurchaseResponse(Long productId, String title, OrderStatus status, int sellPrice, String imageUrl) {
        this.productId = productId;
        this.title = title;
        this.status = status;
        this.sellPrice = sellPrice;
        this.imageUrl = imageUrl;
    }
}
