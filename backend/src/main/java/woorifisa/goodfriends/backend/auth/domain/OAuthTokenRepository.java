package woorifisa.goodfriends.backend.auth.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import woorifisa.goodfriends.backend.auth.exception.NotFoundOAuthTokenException;

import java.util.Optional;

public interface OAuthTokenRepository extends JpaRepository<OAuthToken, Long> {
    boolean existsByUserId(final Long userId);

    default OAuthToken getByUserId(final Long userId) {
        return findByUserId(userId)
                .orElseThrow(NotFoundOAuthTokenException::new);
    }

    @Query("SELECT o "
            + "FROM OAuthToken o "
            + "WHERE o.user.id = :userId")
    Optional<OAuthToken> findByUserId(final Long userId);
}
