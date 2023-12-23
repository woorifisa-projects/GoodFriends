package woorifisa.goodfriends.backend.profile.dto.response;

import lombok.Getter;
import woorifisa.goodfriends.backend.product.domain.ProductStatus;

@Getter
public class ProfileViewSellList {

    private final Long productId;

    private final String title;

    private final ProductStatus status;

    private final int sellPrice;

    private final String imageUrl;

    public ProfileViewSellList(Long productId, String title, ProductStatus status, int sellPrice, String imageUrl) {
        this.productId = productId;
        this.title = title;
        this.status = status;
        this.sellPrice = sellPrice;
        this.imageUrl = imageUrl;
    }
}
