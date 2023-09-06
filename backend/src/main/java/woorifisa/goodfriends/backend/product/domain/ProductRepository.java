package woorifisa.goodfriends.backend.product.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import woorifisa.goodfriends.backend.product.exception.NotFoundProductException;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    default Product getById(final Long id) {
        return findById(id)
                .orElseThrow(NotFoundProductException::new);
    }

    @Query("SELECT p FROM Product p ORDER BY p.id DESC")
    List<Product> findAllOrderByIdDesc();

    @Query("SELECT p FROM Product p WHERE p.productCategory = :productCategory ORDER BY p.id DESC")
    List<Product> findByProductCategory(ProductCategory productCategory);

    @Query("SELECT p FROM Product p WHERE p.title LIKE CONCAT('%',:keyword,'%') ORDER BY p.id DESC")
    List<Product> findByTitleContains(String keyword);

    @Query("SELECT p FROM Product p  WHERE p.productCategory = :productCategory AND p.title LIKE CONCAT('%',:keyword,'%') ORDER BY p.id DESC")
    List<Product> findByTitleContainsInCategory(ProductCategory productCategory, String keyword);
}
