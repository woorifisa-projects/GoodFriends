package woorifisa.goodfriends.backend.product.dto.response;

import woorifisa.goodfriends.backend.product.domain.Product;
import woorifisa.goodfriends.backend.product.domain.ProductCategory;

import java.time.LocalDateTime;

public class ProductSaveResponse {

    private Long id;

    private ProductCategory productCategories;

    private String title;

    private String description;

    private int sellPrice;

    public ProductSaveResponse(Long id, ProductCategory productCategories, String title, String description, int sellPrice, LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
        this.id = id;
        this.productCategories = productCategories;
        this.title = title;
        this.description = description;
        this.sellPrice = sellPrice;
    }

    public ProductSaveResponse(Product newProduct) {
        this.id = newProduct.getId();
        this.productCategories = newProduct.getProductCategories();
        this.title = newProduct.getTitle();
        this.description = newProduct.getDescription();
        this.sellPrice = newProduct.getSellPrice();
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

    public String getDescription() {
        return description;
    }

    public int getSellPrice() {
        return sellPrice;
    }
}
