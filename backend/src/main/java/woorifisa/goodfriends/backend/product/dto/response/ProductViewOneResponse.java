package woorifisa.goodfriends.backend.product.dto.response;

import woorifisa.goodfriends.backend.product.domain.ProductStatus;
import woorifisa.goodfriends.backend.user.domain.User;

import java.time.LocalDateTime;

public class ProductViewOneResponse {

    private Long id;

    private User user;

    private int productCategoryId;

    private String title;

    private ProductStatus status;

    private int sellPrice;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;

    public ProductViewOneResponse() {
    }

    public ProductViewOneResponse(Long id, User user, int productCategoryId, String title, ProductStatus status, int sellPrice, LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
        this.id = id;
        this.user = user;
        this.productCategoryId = productCategoryId;
        this.title = title;
        this.status = status;
        this.sellPrice = sellPrice;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public int getProductCategoryId() {
        return productCategoryId;
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

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }
}
