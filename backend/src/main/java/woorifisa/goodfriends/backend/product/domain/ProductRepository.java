package woorifisa.goodfriends.backend.product.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import woorifisa.goodfriends.backend.product.exception.NotFoundProductException;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    default Product getById(final Long id) {
        return findById(id)
                .orElseThrow(NotFoundProductException::new);
    }

    @Query(value = "SELECT p FROM Product p ORDER BY p.id DESC",
            countQuery = "SELECT count(p) FROM Product p")
    List<Product> findAllOrderByIdDesc(Pageable pageable);

    @Query(value = "SELECT p FROM Product p WHERE p.productCategory = :productCategory ORDER BY p.id DESC",
            countQuery = "SELECT count(p) FROM Product p")
    List<Product> findByProductCategory(Pageable pageable, ProductCategory productCategory);

    @Query("SELECT p FROM Product p WHERE p.title LIKE CONCAT('%',:keyword,'%') ORDER BY p.id DESC")
    List<Product> findByTitleContains(String keyword);

    @Query("SELECT p FROM Product p  WHERE p.productCategory = :productCategory AND p.title LIKE CONCAT('%',:keyword,'%') ORDER BY p.id DESC")
    List<Product> findByTitleContainsInCategory(ProductCategory productCategory, String keyword);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Product p " +
            "SET p.status = :productStatus " +
            "WHERE p.id = :productId")
    void updateProductStatus(Long productId, ProductStatus productStatus);

    @Query("SELECT p FROM Product p " +
            "JOIN FETCH User u " +
            "ON p.id = :productId " +
            "AND p.user.id = u.id")
    Product getByProductIdAndUserId(@Param("productId") Long productId);

    @Query("SELECT p FROM Product p WHERE p.user.id = :userId ORDER BY p.id DESC")
    List<Product> findAllByUserId(Long userId);

    @Query("SELECT p FROM Product p WHERE p.status = :status AND p.user.id = :userId ORDER BY p.id DESC")
    List<Product> findAllByProductStatusAndUserId(ProductStatus status, Long userId);
}
