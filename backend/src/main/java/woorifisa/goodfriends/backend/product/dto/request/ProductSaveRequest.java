package woorifisa.goodfriends.backend.product.dto.request;

import woorifisa.goodfriends.backend.product.domain.ProductCategory;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductSaveRequest {

    @NotBlank(message = "상품 제목을 입력해 주세요.")
    private String title;

    ProductCategory productCategories;

    @NotBlank(message = "상품 설명을 입력해 주세요.")
    private String description;
    @NotNull(message = "상품 가격을 입력해 주세요.")
    private int sellPrice;

    public ProductSaveRequest() {
    }

    public ProductSaveRequest(String title, ProductCategory productCategories, String description, int sellPrice) {
        this.title = title;
        this.productCategories = productCategories;
        this.description = description;
        this.sellPrice = sellPrice;
    }

    public String getTitle() {
        return title;
    }

    public ProductCategory getProductCategories() {
        return productCategories;
    }


    public String getDescription() {
        return description;
    }

    public int getSellPrice() {
        return sellPrice;
    }
}
