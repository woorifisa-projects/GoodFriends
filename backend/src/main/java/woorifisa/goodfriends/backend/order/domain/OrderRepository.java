package woorifisa.goodfriends.backend.order.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    Order findByProductIdAndUserId(Long productId, Long userId);

    List<Order> findByProductId(Long productId);

}
