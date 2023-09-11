package woorifisa.goodfriends.backend.profile.dto.response;

import lombok.Getter;
import woorifisa.goodfriends.backend.order.domain.ConfirmStatus;

@Getter
public class ProductViewPurchaseList {
    private final Long productId;

    private final String title;

    private final ConfirmStatus status;

    private final int sellPrice;

    private final String imageUrl;

    public ProductViewPurchaseList(Long productId, String title, ConfirmStatus status, int sellPrice, String imageUrl) {
        this.productId = productId;
        this.title = title;
        this.status = status;
        this.sellPrice = sellPrice;
        this.imageUrl = imageUrl;
    }
}
