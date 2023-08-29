package woorifisa.goodfriends.backend.admin.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String root;

    private String password;

    protected Admin() {
    }

    public Long getId() {
        return id;
    }

    public String getRoot() {
        return root;
    }

    public String getPassword() {
        return password;
    }

}
