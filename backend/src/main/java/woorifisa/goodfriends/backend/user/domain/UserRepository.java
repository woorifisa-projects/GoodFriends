package woorifisa.goodfriends.backend.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import woorifisa.goodfriends.backend.user.exception.NotFoundUserException;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    default User getById(final Long id) {
        return findById(id)
                .orElseThrow(NotFoundUserException::new);
    }
    boolean existsByEmail(final String email);

    default User getByEmail(final String email) {
        return findByEmail(email)
                .orElseThrow(NotFoundUserException::new);
    }
    Optional<User> findByEmail(final String email);

    default void validateExistById(final Long id) {
        if(!existsById(id)) {
            throw new NotFoundUserException();
        }
    }
}
