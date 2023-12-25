package woorifisa.goodfriends.backend.product.dto.response;

import lombok.Builder;
import lombok.Getter;
import woorifisa.goodfriends.backend.product.domain.Product;
import woorifisa.goodfriends.backend.product.domain.ProductCategory;
import woorifisa.goodfriends.backend.product.domain.ProductStatus;

import java.util.List;

@Getter
public class ProductUpdateResponse {

    private Long id;
    private ProductCategory productCategory;
    private String title;
    private ProductStatus status;
    private String description;
    private int sellPrice;
    private List<String> imageUrls;

    @Builder
    public ProductUpdateResponse(final Long id, final ProductCategory productCategory,
                                 final String title, final ProductStatus status,
                                 final String description, final int sellPrice, final List<String> imageUrls) {
        this.id = id;
        this.productCategory = productCategory;
        this.title = title;
        this.status = status;
        this.description = description;
        this.sellPrice = sellPrice;
        this.imageUrls = imageUrls;
    }

    public ProductUpdateResponse(final Product product, final List<String> imageUrls) {
        this.id = product.getId();
        this.productCategory = product.getProductCategory();
        this.title = product.getTitle();
        this.status = product.getStatus();
        this.description = product.getDescription();
        this.sellPrice = product.getSellPrice();
        this.imageUrls = imageUrls;
    }

    public static ProductUpdateResponse of(final Product product, final List<String> imagesUrls) {
        return ProductUpdateResponse.builder()
                .id(product.getId())
                .productCategory(product.getProductCategory())
                .title(product.getTitle())
                .status(product.getStatus())
                .description(product.getDescription())
                .sellPrice(product.getSellPrice())
                .imageUrls(imagesUrls)
                .build();
    }
}