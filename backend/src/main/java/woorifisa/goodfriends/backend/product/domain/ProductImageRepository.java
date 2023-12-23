package woorifisa.goodfriends.backend.product.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    @Query(value = "select pi.image_url from product_images pi where pi.product_id = :productId limit 1", nativeQuery = true)
    String findOneImageUrlByProductId(@Param("productId") final Long productId);

    @Query("select pi.imageUrl from ProductImage pi where pi.product.id = :productId")
    List<String> findAllImageUrlByProductId(@Param("productId") final Long productId);

    List<ProductImage> findByProductId(final Long productId);

    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM product_images WHERE product_id = :productId", nativeQuery = true)
    void deleteByProductId(final @Param("productId") Long productId);

}