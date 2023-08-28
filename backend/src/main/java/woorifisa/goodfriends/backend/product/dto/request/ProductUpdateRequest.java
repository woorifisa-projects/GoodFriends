package woorifisa.goodfriends.backend.product.dto.request;

import org.springframework.web.multipart.MultipartFile;
import woorifisa.goodfriends.backend.product.domain.ProductCategory;
import woorifisa.goodfriends.backend.product.domain.ProductStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ProductUpdateRequest {

    @NotBlank(message = "상품 제목을 입력해 주세요.")
    private String title;

    private ProductCategory productCategory;

    @NotBlank(message = "상품 설명을 입력해 주세요.")
    private String description;

    @NotNull(message = "상품 가격을 입력해 주세요.")
    private int sellPrice;

    private List<MultipartFile> imageUrls;

    public ProductUpdateRequest() {
    }

    public ProductUpdateRequest(String title, ProductCategory productCategory, String description, int sellPrice) {
        this.title = title;
        this.productCategory = productCategory;
        this.description = description;
        this.sellPrice = sellPrice;
    }

    public ProductUpdateRequest(String title, ProductCategory productCategory, String description, int sellPrice, List<MultipartFile> imageUrls) {
        this.title = title;
        this.productCategory = productCategory;
        this.description = description;
        this.sellPrice = sellPrice;
        this.imageUrls = imageUrls;
    }


    public String getTitle() {
        return title;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public String getDescription() {
        return description;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public List<MultipartFile> getImageUrls() {
        return imageUrls;
    }

}
