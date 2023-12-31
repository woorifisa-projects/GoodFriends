package woorifisa.goodfriends.backend.order.domain;

import lombok.Builder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import woorifisa.goodfriends.backend.common.BaseEntity;
import woorifisa.goodfriends.backend.product.domain.Product;
import woorifisa.goodfriends.backend.user.domain.User;

import javax.persistence.*;

@Table(name = "orders")
@Entity
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // 외래 키로 사용할 컬럼 지정
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Column(name = "confirm_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(nullable = false)
    private String possibleDate;

    @Column(nullable = false)
    private String possibleTime;

    private String requirements;

    protected Order() {
    }

    @Builder
    public Order(final Product product, final User user, final OrderStatus orderStatus
            , final String possibleDate, final String possibleTime, final String requirements) {
        this.product = product;
        this.user = user;
        this.orderStatus = orderStatus;
        this.possibleDate = possibleDate;
        this.possibleTime = possibleTime;
        this.requirements = requirements;
    }

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public User getUser() {
        return user;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public String getPossibleDate() {
        return possibleDate;
    }

    public String getPossibleTime() {
        return possibleTime;
    }

    public String getRequirements() {
        return requirements;
    }

}
