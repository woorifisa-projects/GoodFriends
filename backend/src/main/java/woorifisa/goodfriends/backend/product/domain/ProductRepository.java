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

    @Query("SELECT p " +
            "FROM Product p " +
            "ORDER BY p.id DESC")
    List<Product> findAllOrderByIdDesc(Pageable pageable);

    @Query("SELECT p " +
            "FROM Product p " +
            "WHERE p.productCategory = :productCategory " +
            "ORDER BY p.id DESC")
    List<Product> findByProductCategory(Pageable pageable, @Param("productCategory") final ProductCategory productCategory);

    @Query("SELECT p " +
            "FROM Product p " +
            "WHERE p.title LIKE CONCAT('%',:keyword,'%') " +
            "ORDER BY p.id DESC")
    List<Product> findByTitleContains(Pageable pageable, @Param("keyword") final String keyword);

    @Query("SELECT p " +
            "FROM Product p  " +
            "WHERE p.productCategory = :productCategory " +
            "AND p.title LIKE CONCAT('%',:keyword,'%') " +
            "ORDER BY p.id DESC")
    List<Product> findByTitleContainsInCategory(Pageable pageable, @Param("productCategory") final ProductCategory productCategory, @Param("keyword") final String keyword);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Product p " +
            "SET p.status = :productStatus " +
            "WHERE p.id = :productId")
    void updateProductStatus(@Param("productId") final Long productId, @Param("productStatus") final ProductStatus productStatus);

    @Query("SELECT p FROM Product p " +
            "JOIN FETCH User u " +
            "ON p.user.id = u.id " +
            "AND p.id = :productId")
    Product getByProductIdAndUserId(@Param("productId") final Long productId);

    @Query("SELECT p " +
            "FROM Product p " +
            "WHERE p.user.id = :userId " +
            "ORDER BY p.id DESC")
    List<Product> findAllByUserId(@Param("userId") final Long userId);

    @Query("SELECT p " +
            "FROM Product p " +
            "WHERE p.status = :status " +
            "AND p.user.id = :userId " +
            "ORDER BY p.id DESC")
    List<Product> findAllByProductStatusAndUserId(@Param("status") final ProductStatus status, @Param("userId") final Long userId);

    @Query("SELECT count(p) " +
            "FROM Product p " +
            "WHERE p.status = :status " +
            "AND p.user.id = :userId")
    Long findCountByProductStatusAndUserId(@Param("status") final ProductStatus status, @Param("userId") final Long userId);
}
