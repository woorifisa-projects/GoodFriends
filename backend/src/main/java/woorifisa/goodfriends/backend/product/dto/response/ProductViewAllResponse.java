package woorifisa.goodfriends.backend.product.dto.response;

import woorifisa.goodfriends.backend.product.domain.ProductCategory;
import woorifisa.goodfriends.backend.product.domain.ProductStatus;

import java.util.List;

public class ProductViewAllResponse {

    private Long id;

    private ProductCategory productCategory;

    private String title;

    private ProductStatus status;

    private int sellPrice;

    private String imageUrl;

    private String address;

    public ProductViewAllResponse() {
    }

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
