package woorifisa.goodfriends.backend.product.domain;

import javax.persistence.*;

@Table(name = "profile_images")
@Entity
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public ProductImage() {
    }

    public ProductImage(Product product) {
        this.product = product;
    }
}
