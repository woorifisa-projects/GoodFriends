package woorifisa.goodfriends.backend.product.dto.response;

import lombok.Builder;
import lombok.Getter;
import woorifisa.goodfriends.backend.product.domain.Product;
import woorifisa.goodfriends.backend.product.domain.ProductCategory;
import woorifisa.goodfriends.backend.product.domain.ProductStatus;
import woorifisa.goodfriends.backend.profile.domain.Profile;
import woorifisa.goodfriends.backend.user.domain.User;

@Getter
public class ProductResponse {

    private final Long id;
    private final ProductCategory productCategory;
    private final String title;
    private final ProductStatus status;
    private final int sellPrice;
    private final String imageUrl;
    private final String address;
    private final boolean activated;

    @Builder
    public ProductResponse(final Long id, final ProductCategory productCategory, final String title, final ProductStatus status, final int sellPrice, String imageUrl, String address, boolean activated) {
        this.id = id;
        this.productCategory = productCategory;
        this.title = title;
        this.status = status;
        this.sellPrice = sellPrice;
        this.imageUrl = imageUrl;
        this.address = address;
        this.activated = activated;
    }

    public static ProductResponse of(final Product product, final String imageUrl) {
        return ProductResponse.builder()
                .id(product.getId())
                .productCategory(product.getProductCategory())
                .title(product.getTitle())
                .status(product.getStatus())
                .sellPrice(product.getSellPrice())
                .imageUrl(imageUrl)
                .address(null)
                .activated(true)
                .build();
    }

    public static ProductResponse of(final Product product, final String imageUrl, final User user, final Profile profile) {
        return ProductResponse.builder()
                .id(product.getId())
                .productCategory(product.getProductCategory())
                .title(product.getTitle())
                .status(product.getStatus())
                .sellPrice(product.getSellPrice())
                .imageUrl(imageUrl)
                .address(profile.getAddress())
                .activated(user.isActivated())
                .build();
    }
}
