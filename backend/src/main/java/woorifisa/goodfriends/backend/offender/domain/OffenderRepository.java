package woorifisa.goodfriends.backend.offender.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import woorifisa.goodfriends.backend.user.domain.User;

import java.util.Optional;

public interface OffenderRepository extends JpaRepository<Offender, Long> {
    Optional<Offender> findByUserId(Long userId);
}
