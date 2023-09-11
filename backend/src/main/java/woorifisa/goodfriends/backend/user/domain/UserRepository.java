package woorifisa.goodfriends.backend.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import woorifisa.goodfriends.backend.user.exception.NotFoundUserException;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    default User getById(final Long id) {
        return findById(id)
                .orElseThrow(NotFoundUserException::new);
    }
    Optional<User> findById(Long id);

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
    @Query("SELECT u, p FROM User u JOIN Profile p ON u.id = p.user.id")
    List<Object[]> getAllUserInfo();

    @Query("SELECT u.ban FROM User u WHERE u.id = :userId")
    Long findBanById(Long userId);
}
