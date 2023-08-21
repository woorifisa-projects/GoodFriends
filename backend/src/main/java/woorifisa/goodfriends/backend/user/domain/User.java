package woorifisa.goodfriends.backend.user.domain;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import woorifisa.goodfriends.backend.user.exception.InvalidUserException;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@EntityListeners(AuditingEntityListener.class) // 3, 4
public class User {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-z0-9._-]+@[a-z]+[.]+[a-z]{2,3}$");
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Embedded
    private Nickname nickname;

    private int ban;

    @CreatedDate // 1
    private LocalDateTime createdAt;

    @LastModifiedDate // 1
    private LocalDateTime lastModifiedAt;

    protected User() {
    }

    public User(String email, Nickname nickname, int ban) {
        validateEmail(email);

        this.email = email;
        this.nickname = nickname;
        this.ban = ban;
    }

    public User(String email, String nickname, int ban) {
        validateEmail(email);

        this.email = email;
        this.nickname = new Nickname(nickname); // 문자열로부터 Nickname 객체 생성
        this.ban = ban;
    }

    private void validateEmail(final String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        if (!matcher.matches()) {
            throw new InvalidUserException();
        }
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
