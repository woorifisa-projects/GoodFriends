package woorifisa.goodfriends.backend.admin.domain;

import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String adminId;

    private String password;

    public Admin() {
    }

    public Admin(Long id, String adminId, String password) {
        this.id = id;
        this.adminId = adminId;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getAdminId() {
        return adminId;
    }

    public String getPassword() {
        return password;
    }
}
