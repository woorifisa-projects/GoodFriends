package woorifisa.goodfriends.backend.offender.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OffenderRepository extends JpaRepository<Offender, Long> {

    @Query("SELECT o " +
            "FROM Offender o " +
            "WHERE o.user.id = :userId")
    Optional<Offender> findByUserId(@Param("userId") Long userId);
}