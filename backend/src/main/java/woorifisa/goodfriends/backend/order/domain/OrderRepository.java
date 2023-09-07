package woorifisa.goodfriends.backend.order.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import woorifisa.goodfriends.backend.order.exception.NotFoundOrderException;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    Order findByProductIdAndUserId(Long productId, Long userId);

    @Query("SELECT o FROM Order o " +
            "JOIN FETCH User u " +
            "ON o.product.id = :productId " +
            "AND o.user.id = u.id")
    List<Order> findOrdersAndUserByProductId(Long productId);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Order o " +
            "SET o.confirmStatus = :confirmStatus " +
            "WHERE o.id = :orderId")
    void updateConfirmStatus(Long orderId, ConfirmStatus confirmStatus);

    default Order getById(Long orderId) {
        return findById(orderId).orElseThrow(NotFoundOrderException::new);
    }
}
