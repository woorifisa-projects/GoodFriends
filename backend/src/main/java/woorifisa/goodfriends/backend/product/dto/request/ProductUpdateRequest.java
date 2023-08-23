package woorifisa.goodfriends.backend.product.dto.request;

import woorifisa.goodfriends.backend.product.domain.ProductCategory;
import woorifisa.goodfriends.backend.product.domain.ProductStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductUpdateRequest {

    @NotBlank(message = "상품 제목을 입력해 주세요.")
    private String title;

    ProductCategory productCategories;

    private ProductStatus status;

    @NotBlank(message = "상품 설명을 입력해 주세요.")
    private String description;

    @NotNull(message = "상품 가격을 입력해 주세요.")
    private int sellPrice;

    public ProductUpdateRequest() {
    }

    public ProductUpdateRequest(String title, ProductCategory productCategories, ProductStatus status, String description, int sellPrice) {
        this.title = title;
        this.productCategories = productCategories;
        this.status = status;
        this.description = description;
        this.sellPrice = sellPrice;
    }


    public String getTitle() {
        return title;
    }

    public ProductCategory getProductCategories() {
        return productCategories;
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
