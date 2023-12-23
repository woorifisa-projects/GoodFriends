package woorifisa.goodfriends.backend.product.dto.request;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;
import woorifisa.goodfriends.backend.product.domain.ProductCategory;

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

    public ProductCreateRequest() {
    }

    public ProductCreateRequest(String title, ProductCategory productCategory, String description, int sellPrice) {
        this.title = title;
        this.productCategory = productCategory;
        this.description = description;
        this.sellPrice = sellPrice;
    }

    public ProductCreateRequest(String title, ProductCategory productCategory, String description, int sellPrice, List<MultipartFile> imageUrls) {
        this.title = title;
        this.productCategory = productCategory;
        this.description = description;
        this.sellPrice = sellPrice;
        this.imageUrls = imageUrls;
    }


}
