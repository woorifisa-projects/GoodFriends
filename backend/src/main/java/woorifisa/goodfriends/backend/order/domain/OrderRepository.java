package woorifisa.goodfriends.backend.order.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    Order findByProductIdAndUserId(Long productId, Long userId);

    @Query("SELECT o FROM Order o " +
            "JOIN FETCH User u " +
            "ON o.product.id = :productId " +
            "AND o.user.id = u.id")
    List<Order> findordersInfoByProductId(Long productId);

}
