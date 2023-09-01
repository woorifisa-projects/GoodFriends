package woorifisa.goodfriends.backend.product.dto.response;

import woorifisa.goodfriends.backend.product.domain.ProductCategory;
import woorifisa.goodfriends.backend.product.domain.ProductStatus;

import java.util.List;

public class ProductViewAllResponse {

    private final Long id;

    private final ProductCategory productCategory;

    private final String title;

    private final ProductStatus status;

    private final int sellPrice;

    private final String imageUrl;

    private final String address;

    public ProductViewAllResponse(Long id, ProductCategory productCategory, String title, ProductStatus status, int sellPrice, String imageUrl, String address) {
        this.id = id;
        this.productCategory = productCategory;
        this.title = title;
        this.status = status;
        this.sellPrice = sellPrice;
        this.imageUrl = imageUrl;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public String getTitle() {
        return title;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getAddress() {
        return address;
    }

}
