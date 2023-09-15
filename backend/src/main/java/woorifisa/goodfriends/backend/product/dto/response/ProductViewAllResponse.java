package woorifisa.goodfriends.backend.product.dto.response;

import lombok.Getter;
import woorifisa.goodfriends.backend.product.domain.ProductCategory;
import woorifisa.goodfriends.backend.product.domain.ProductStatus;

import java.util.List;

@Getter
public class ProductViewAllResponse {

    private final Long id;

    private final ProductCategory productCategory;

    private final String title;

    private final ProductStatus status;

    private final int sellPrice;

    private final String imageUrl;

    private final String address;

    private final boolean activated;

    public ProductViewAllResponse(Long id, ProductCategory productCategory, String title, ProductStatus status, int sellPrice, String imageUrl, String address, boolean activated) {
        this.id = id;
        this.productCategory = productCategory;
        this.title = title;
        this.status = status;
        this.sellPrice = sellPrice;
        this.imageUrl = imageUrl;
        this.address = address;
        this.activated = activated;
    }

}
