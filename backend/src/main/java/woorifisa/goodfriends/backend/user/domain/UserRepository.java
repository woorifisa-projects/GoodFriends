package woorifisa.goodfriends.backend.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import woorifisa.goodfriends.backend.user.exception.NoSuchUserException;

public interface UserRepository extends JpaRepository<User, Long> {

    default User getById(final Long id) {
        return findById(id)
                .orElseThrow(NoSuchUserException::new);
    }
}
