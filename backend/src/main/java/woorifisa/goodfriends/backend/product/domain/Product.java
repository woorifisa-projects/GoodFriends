package woorifisa.goodfriends.backend.product.domain;

import lombok.Builder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import woorifisa.goodfriends.backend.admin.domain.Admin;
import woorifisa.goodfriends.backend.common.BaseEntity;
import woorifisa.goodfriends.backend.product.exception.InvalidDescriptionException;
import woorifisa.goodfriends.backend.user.domain.User;

import javax.persistence.*;

@Table(name = "products")
@Entity
public class Product extends BaseEntity {
    private static final int DESCRIPTION_MINIMUM_SIZE = 10;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // 외래 키로 사용할 컬럼 지정
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int sellPrice;

    public void validDescription(final String description) {
        if (description.length() < DESCRIPTION_MINIMUM_SIZE) {
            throw new InvalidDescriptionException();
        }
    }

    protected Product() {
    }

    @Builder
    public Product(final User user, final Admin admin, final ProductCategory productCategory,
                   final String title, final ProductStatus status, final String description, final int sellPrice) {
        validDescription(description);

        this.user = user;
        this.admin = admin;
        this.productCategory = productCategory;
        this.title = title;
        this.status = status;
        this.description = description;
        this.sellPrice = sellPrice;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Admin getAdmin() {
        return admin;
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

    public void updateUser(final User user) {
        this.user = user;
    }

    public void updateStatus(final ProductStatus status) {
        this.status = status;
    }

    public void updateTitle(final String title) {
        this.title = title;
    }

    public void updateProductCategory(final ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public void updateDescription(final String description) {
        this.description = description;
    }

    public void updateSellPrice(final int sellPrice) {
        this.sellPrice = sellPrice;
    }
}
