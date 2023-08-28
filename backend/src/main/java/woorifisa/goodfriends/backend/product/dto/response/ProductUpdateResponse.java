package woorifisa.goodfriends.backend.product.dto.response;

import woorifisa.goodfriends.backend.product.domain.Product;
import woorifisa.goodfriends.backend.product.domain.ProductCategory;
import woorifisa.goodfriends.backend.product.domain.ProductStatus;

import java.util.List;

public class ProductUpdateResponse {

    private Long id;

    private ProductCategory productCategory;

    private String title;

    private ProductStatus status;

    private String description;

    private int sellPrice;

    private List<String> imageUrls;

    public ProductUpdateResponse() {
    }

    public ProductUpdateResponse(Long id, ProductCategory productCategory, String title, ProductStatus status, String description, int sellPrice, List<String> imageUrls) {
        this.id = id;
        this.productCategory = productCategory;
        this.title = title;
        this.status = status;
        this.description = description;
        this.sellPrice = sellPrice;
        this.imageUrls = imageUrls;
    }

    public ProductUpdateResponse(Product product, List<String> imageUrls){
        this.id = product.getId();
        this.productCategory = product.getProductCategory();
        this.title = product.getTitle();
        this.status = product.getStatus();
        this.description = product.getDescription();
        this.sellPrice = product.getSellPrice();
        this.imageUrls = imageUrls;
    }

    public Long getId() {
        return id;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
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

    public List<String> getImageUrls() {
        return imageUrls;
    }
}