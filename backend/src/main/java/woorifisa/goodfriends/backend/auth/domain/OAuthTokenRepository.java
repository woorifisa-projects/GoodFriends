package woorifisa.goodfriends.backend.auth.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import woorifisa.goodfriends.backend.auth.exception.NotFoundOAuthTokenException;

import javax.transaction.Transactional;
import java.util.Optional;

public interface OAuthTokenRepository extends JpaRepository<OAuthToken, Long> {
    boolean existsByUserId(final Long userId);

    default OAuthToken getByUserId(final Long userId) {
        return findByUserId(userId)
                .orElseThrow(NotFoundOAuthTokenException::new);
    }

    // MySQL 쿼리를 사용
    @Query("SELECT o "
            + "FROM OAuthToken o "
            + "WHERE o.user.id = :userId")
    Optional<OAuthToken> findByUserId(final Long userId);

    // MySQL 쿼리를 사용
    @Modifying
    @Transactional
    @Query("DELETE FROM OAuthToken o WHERE o.user.id = :userId")
    void deleteAllByUserId(Long userId);
}
