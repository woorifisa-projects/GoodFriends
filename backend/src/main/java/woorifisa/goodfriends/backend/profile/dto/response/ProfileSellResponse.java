package woorifisa.goodfriends.backend.profile.dto.response;

import lombok.Builder;
import lombok.Getter;
import woorifisa.goodfriends.backend.product.domain.Product;
import woorifisa.goodfriends.backend.product.domain.ProductStatus;

@Getter
public class ProfileSellResponse {

    private final Long productId;
    private final String title;
    private final ProductStatus status;
    private final int sellPrice;
    private final String imageUrl;

    @Builder
    public ProfileSellResponse(final Long productId, final String title, final ProductStatus status, final int sellPrice, final String imageUrl) {
        this.productId = productId;
        this.title = title;
        this.status = status;
        this.sellPrice = sellPrice;
        this.imageUrl = imageUrl;
    }
}
