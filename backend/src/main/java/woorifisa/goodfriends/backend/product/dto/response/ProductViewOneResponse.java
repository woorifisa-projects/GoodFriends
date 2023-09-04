package woorifisa.goodfriends.backend.product.dto.response;

import woorifisa.goodfriends.backend.product.domain.ProductCategory;
import woorifisa.goodfriends.backend.product.domain.ProductStatus;

import java.time.LocalDateTime;
import java.util.List;

public class ProductViewOneResponse {

    private Long id;

    private Long userId;

    private Long adminId;

    private ProductCategory productCategory;

    private String title;

    private String description;

    private ProductStatus status;

    private int sellPrice;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;

    private List<String> imageUrls;

    private String profileImageUrl;

    private String nickName;

    public ProductViewOneResponse() {
    }

    public ProductViewOneResponse(Long id, Long userId, Long adminId, ProductCategory productCategory, String title, String description, ProductStatus status, int sellPrice, LocalDateTime createdDate, LocalDateTime lastModifiedDate, List<String> imageUrls, String profileImageUrl, String nickName) {
        this.id = id;
        this.userId = userId;
        this.adminId = adminId;
        this.productCategory = productCategory;
        this.title = title;
        this.description = description;
        this.status = status;
        this.sellPrice = sellPrice;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.imageUrls = imageUrls;
        this.profileImageUrl = profileImageUrl;
        this.nickName = nickName;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public Long getAdminId() {
        return adminId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
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

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }
}