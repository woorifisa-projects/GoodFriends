package woorifisa.goodfriends.backend.product.dto.response;

import woorifisa.goodfriends.backend.product.domain.ProductStatus;
import woorifisa.goodfriends.backend.user.domain.User;

import java.time.LocalDateTime;
import java.util.List;

public class ProductViewOneResponse {

    private Long id;

    private User user;

    private int productCategoryId;

    private String title;

    private ProductStatus status;

    private int sellPrice;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;

    private List<String> imageUrls;

    public ProductViewOneResponse() {
    }

    public ProductViewOneResponse(Long id, User user, int productCategoryId, String title, ProductStatus status, int sellPrice, LocalDateTime createdDate, LocalDateTime lastModifiedDate, List<String> imageUrls) {
        this.id = id;
        this.user = user;
        this.productCategoryId = productCategoryId;
        this.title = title;
        this.status = status;
        this.sellPrice = sellPrice;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.imageUrls = imageUrls;
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

    public List<String> getImageUrls() {
        return imageUrls;
    }

}
