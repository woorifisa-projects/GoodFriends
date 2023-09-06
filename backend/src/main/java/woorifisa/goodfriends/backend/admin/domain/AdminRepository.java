package woorifisa.goodfriends.backend.admin.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import woorifisa.goodfriends.backend.admin.exception.NotFoundAdminException;
import woorifisa.goodfriends.backend.user.domain.User;

import java.util.Optional;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Long> {


    Admin findByRoot(String root);

    default Admin getByRoot(String root){
        Admin admin = findByRoot(root);
        if(admin.getId() == null) {
            throw new NotFoundAdminException();
        }
        return admin;
    }

    default Admin getById(Long adminId){
        return findById(adminId)
                .orElseThrow(NotFoundAdminException::new);
    }
}
