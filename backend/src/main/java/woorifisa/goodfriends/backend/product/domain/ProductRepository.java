package woorifisa.goodfriends.backend.product.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import woorifisa.goodfriends.backend.product.exception.NotFoundProductException;

public interface ProductRepository extends JpaRepository<Product, Long> {
    default Product getById(final Long id) {
        return findById(id)
                .orElseThrow(NotFoundProductException::new);
    }
}
