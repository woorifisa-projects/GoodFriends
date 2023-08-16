package woorifisa.goodfriends.backend.user.domain;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class) // 3, 4
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    private Nickname nickname;

    private int ban;

    @CreatedDate // 1
    private LocalDateTime createdAt;

    @LastModifiedDate // 1
    private LocalDateTime lastModifiedAt;

    protected User() {
    }

    public User(final String email, final Nickname nickname, int ban) {

        this.email = email;
        this.nickname = nickname;
        this.ban = ban;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Nickname getNickname() {
        return nickname;
    }

    public int getBan() {
        return ban;
    }

}
