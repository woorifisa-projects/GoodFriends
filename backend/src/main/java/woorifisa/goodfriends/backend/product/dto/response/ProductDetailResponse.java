package woorifisa.goodfriends.backend.product.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import woorifisa.goodfriends.backend.product.domain.Product;
import woorifisa.goodfriends.backend.product.domain.ProductCategory;
import woorifisa.goodfriends.backend.product.domain.ProductStatus;
import woorifisa.goodfriends.backend.user.domain.User;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ProductDetailResponse {

    private Long id;
    private Long userId;
    private String nickName;
    private String profileImageUrl;
    private Long adminId;
    private ProductCategory productCategory;
    private String title;
    private ProductStatus status;
    private String description;
    private int sellPrice;
    private List<String> imageUrls;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime createdDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime lastModifiedDate;

    @Builder
    public ProductDetailResponse(final Long id, final Long userId, final String nickName, final String profileImageUrl, final Long adminId,
                                 final ProductCategory productCategory, final String title, final ProductStatus status,
                                 final String description, final int sellPrice, final List<String> imageUrls,
                                 final LocalDateTime createdDate, final LocalDateTime lastModifiedDate) {
        this.id = id;
        this.userId = userId;
        this.nickName = nickName;
        this.profileImageUrl = profileImageUrl;
        this.adminId = adminId;
        this.productCategory = productCategory;
        this.title = title;
        this.status = status;
        this.description = description;
        this.sellPrice = sellPrice;
        this.imageUrls = imageUrls;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public static ProductDetailResponse of(final Product product, final List<String> imageUrls) {
        return ProductDetailResponse.builder()
                .id(product.getId())
                .userId(null)
                .nickName("관리자")
                .profileImageUrl(null)
                .adminId(product.getAdmin().getId())
                .productCategory(product.getProductCategory())
                .title(product.getTitle())
                .status(product.getStatus())
                .description(product.getDescription())
                .sellPrice(product.getSellPrice())
                .imageUrls(imageUrls)
                .createdDate(product.getCreatedAt())
                .lastModifiedDate(product.getLastModifiedAt())
                .build();
    }

    public static ProductDetailResponse of(final Product product, final List<String> imageUrls, final User user) {
        return ProductDetailResponse.builder()
                .id(product.getId())
                .userId(product.getUser().getId())
                .nickName(user.getNickname())
                .profileImageUrl(user.getProfileImageUrl())
                .adminId(null)
                .productCategory(product.getProductCategory())
                .title(product.getTitle())
                .status(product.getStatus())
                .description(product.getDescription())
                .sellPrice(product.getSellPrice())
                .imageUrls(imageUrls)
                .createdDate(product.getCreatedAt())
                .lastModifiedDate(product.getLastModifiedAt())
                .build();
    }
}