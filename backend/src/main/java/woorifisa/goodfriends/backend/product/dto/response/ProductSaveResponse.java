package woorifisa.goodfriends.backend.product.dto.response;

import woorifisa.goodfriends.backend.product.domain.Product;
import woorifisa.goodfriends.backend.product.domain.ProductCategory;
import woorifisa.goodfriends.backend.product.domain.ProductStatus;

import java.time.LocalDateTime;

public class ProductSaveResponse {

    private Long id;

    private ProductCategory productCategories;

    private String title;

    private ProductStatus status;

    private String description;

    private int sellPrice;

    public ProductSaveResponse(Long id, ProductCategory productCategories, String title, ProductStatus status, String description, int sellPrice, LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
        this.id = id;
        this.productCategories = productCategories;
        this.title = title;
        this.status = status;
        this.description = description;
        this.sellPrice = sellPrice;
    }

    public ProductSaveResponse(Product newProduct) {
        this.id = newProduct.getId();
        this.productCategories = newProduct.getProductCategories();
        this.title = newProduct.getTitle();
        this.status = newProduct.getStatus();
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
