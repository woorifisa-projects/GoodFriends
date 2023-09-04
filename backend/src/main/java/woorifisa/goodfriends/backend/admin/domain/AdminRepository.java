package woorifisa.goodfriends.backend.admin.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import woorifisa.goodfriends.backend.user.domain.User;

import java.util.Optional;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Long> {

    Optional<Admin> findByRoot(String root);

    Optional<Admin> findById(Long adminId);
}
