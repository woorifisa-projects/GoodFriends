package woorifisa.goodfriends.backend.user.domain;

import woorifisa.goodfriends.backend.global.common.BaseTimeEntity;
import woorifisa.goodfriends.backend.user.exception.InvalidUserException;

import javax.persistence.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Table(name = "users")
@Entity
public class User extends BaseTimeEntity {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-z0-9._-]+@[a-z]+[.]+[a-z]{2,3}$");
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "profile_image_url", nullable = false)
    private String profileImageUrl;

    private int ban;

    protected User() {
    }

    public User(String email, String nickname, String profileImageUrl) {
        this.email = email;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
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

    public String getNickname() {
        return nickname;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public int getBan() {
        return ban;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }
    public void updateProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }
}
