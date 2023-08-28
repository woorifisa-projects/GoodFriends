package woorifisa.goodfriends.backend.product.dto.response;

import woorifisa.goodfriends.backend.product.domain.Product;
import woorifisa.goodfriends.backend.product.domain.ProductCategory;

import java.time.LocalDateTime;
import java.util.List;

public class ProductSaveResponse {

    private Long id;

    private ProductCategory productCategory;

    private String title;

    private String description;

    private int sellPrice;

    private List<String> imageUrls;

    public ProductSaveResponse() {
    }

    public ProductSaveResponse(Long id, ProductCategory productCategory, String title, String description, int sellPrice, LocalDateTime createdDate, LocalDateTime lastModifiedDate, List<String> imageUrls) {
        this.id = id;
        this.productCategory = productCategory;
        this.title = title;
        this.description = description;
        this.sellPrice = sellPrice;
        this.imageUrls = imageUrls;
    }

    public ProductSaveResponse(Product newProduct, List<String> imageUrls) {
        this.id = newProduct.getId();
        this.productCategory = newProduct.getProductCategory();
        this.title = newProduct.getTitle();
        this.description = newProduct.getDescription();
        this.sellPrice = newProduct.getSellPrice();
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
