package woorifisa.goodfriends.backend.product.dto.response;

import woorifisa.goodfriends.backend.product.domain.Product;
import woorifisa.goodfriends.backend.product.domain.ProductCategory;
import woorifisa.goodfriends.backend.product.domain.ProductStatus;

public class ProductUpdateResponse {

    private Long id;

    private ProductCategory productCategories;

    private String title;

    private ProductStatus status;

    private String description;

    private int sellPrice;

    public ProductUpdateResponse(Long id, ProductCategory productCategories, String title, ProductStatus status, String description, int sellPrice) {
        this.id = id;
        this.productCategories = productCategories;
        this.title = title;
        this.status = status;
        this.description = description;
        this.sellPrice = sellPrice;
    }

    public ProductUpdateResponse(Product product){
        this.id = product.getId();
        this.productCategories = product.getProductCategories();
        this.title = product.getTitle();
        this.status = product.getStatus();
        this.description = product.getDescription();
        this.sellPrice = product.getSellPrice();
    }

    public Long getId() {
        return id;
    }

    public ProductCategory getProductCategories() {
        return productCategories;
    }

    public String getTitle() {
        return title;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public int getSellPrice() {
        return sellPrice;
    }
}
