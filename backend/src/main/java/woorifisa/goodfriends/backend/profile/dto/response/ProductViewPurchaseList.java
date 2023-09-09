package woorifisa.goodfriends.backend.profile.dto.response;

import lombok.Getter;
import woorifisa.goodfriends.backend.order.domain.ConfirmStatus;
import woorifisa.goodfriends.backend.product.domain.ProductStatus;

@Getter
public class ProductViewPurchaseList {
    private final Long id;

    private final String title;

    private final ConfirmStatus status;

    private final int sellPrice;

    private final String imageUrl;

    public ProductViewPurchaseList(Long id, String title, ConfirmStatus status, int sellPrice, String imageUrl) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.sellPrice = sellPrice;
        this.imageUrl = imageUrl;
    }
}
