package woorifisa.goodfriends.backend.profile.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import woorifisa.goodfriends.backend.profile.exception.NotFoundProfile;

import java.util.Optional;


public interface ProfileRepository extends JpaRepository<Profile, Long> {

    @Query("SELECT p " +
            "FROM Profile p " +
            "WHERE p.user.id = :userId")
    Optional<Profile> findByUserId(@Param("userId") Long userId);

    default Profile getByUserId(Long userId) {
        return findByUserId(userId).orElseThrow(NotFoundProfile::new);
    }
}
