package woorifisa.goodfriends.backend.declaration.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import woorifisa.goodfriends.backend.auth.dto.LoginUser;

public interface DeclarationRepository extends JpaRepository<Declaration, Long> {

    Declaration findByUserIdAndProductId(Long userId, Long productId);
}
