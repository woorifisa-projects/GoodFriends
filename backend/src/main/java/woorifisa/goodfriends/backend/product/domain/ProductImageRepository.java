package woorifisa.goodfriends.backend.product.domain;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    @Query("select pi.imageUrl from ProductImage pi where pi.product.id = :productId")
    List<String> findOneImageUrlByProductId(@Param("productId") Long productId, Pageable pageable);
    @Query("select pi.imageUrl from ProductImage pi where pi.product.id = :productId")
    List<String> findAllImageUrlByProductId(@Param("productId") Long productId);

    List<ProductImage> findByProductId(Long productId);

}
