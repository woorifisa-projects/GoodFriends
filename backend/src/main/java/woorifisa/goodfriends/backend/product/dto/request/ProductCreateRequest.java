package woorifisa.goodfriends.backend.product.dto.request;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;
import woorifisa.goodfriends.backend.product.domain.Product;
import woorifisa.goodfriends.backend.product.domain.ProductCategory;
import woorifisa.goodfriends.backend.product.domain.ProductStatus;
import woorifisa.goodfriends.backend.user.domain.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
public class ProductCreateRequest {

    @NotBlank(message = "상품 제목을 입력해 주세요.")
    private String title;

    private ProductCategory productCategory;

    @NotBlank(message = "상품 설명을 입력해 주세요.")
    private String description;

    @NotNull(message = "상품 가격을 입력해 주세요.")
    private int sellPrice;

    private List<MultipartFile> imageUrls;

    protected ProductCreateRequest() {
    }

    @Builder
    public ProductCreateRequest(final String title, final ProductCategory productCategory,
                                final String description, final int sellPrice,
                                final List<MultipartFile> imageUrls) {
        this.title = title;
        this.productCategory = productCategory;
        this.description = description;
        this.sellPrice = sellPrice;
        this.imageUrls = imageUrls;
    }


    public Product toEntity(final User user, final ProductCreateRequest request) {
        return Product.builder()
                .user(user)
                .title(request.getTitle())
                .status(ProductStatus.SELL)
                .productCategory(request.getProductCategory())
                .description(request.getDescription())
                .sellPrice(request.getSellPrice())
                .build();
    }
}
