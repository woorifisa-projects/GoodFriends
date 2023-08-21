package woorifisa.goodfriends.backend.product.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import woorifisa.goodfriends.backend.user.domain.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "files")
@Entity
@EntityListeners(AuditingEntityListener.class) // 3, 4
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_image_id")
    private ProductImage productImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "type")
    private String type;

    @Column(name = "name")
    private String name;

    @CreatedDate // 1
    private LocalDateTime createdDate;

    public File() {
    }

    public File(ProductImage productImage, User user, String type, String name, LocalDateTime createdDate) {
        this.productImage = productImage;
        this.user = user;
        this.type = type;
        this.name = name;
        this.createdDate = createdDate;
    }

    public int getId() {
        return id;
    }

    public ProductImage getProductImage() {
        return productImage;
    }

    public User getUser() {
        return user;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
}
