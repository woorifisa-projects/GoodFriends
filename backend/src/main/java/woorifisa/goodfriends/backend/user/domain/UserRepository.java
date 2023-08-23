package woorifisa.goodfriends.backend.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import woorifisa.goodfriends.backend.user.exception.NoSuchUserException;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    default User getById(final Long id) {
        return findById(id)
                .orElseThrow(NoSuchUserException::new);
    }
    boolean existsByEmail(final String email);

    default User getByEmail(final String email) {
        return findByEmail(email)
                .orElseThrow(NoSuchUserException::new);
    }
    Optional<User> findByEmail(final String email);
}
