package woorifisa.goodfriends.backend.product.domain;

import javax.persistence.*;

@Table(name = "product_categories")
@Entity
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    protected ProductCategory() {
    }

    public ProductCategory(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
