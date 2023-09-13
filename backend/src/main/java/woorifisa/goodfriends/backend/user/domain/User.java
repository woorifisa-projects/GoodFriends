package woorifisa.goodfriends.backend.user.domain;

import lombok.experimental.SuperBuilder;
import woorifisa.goodfriends.backend.global.common.BaseTimeEntity;
import woorifisa.goodfriends.backend.user.exception.InvalidUserException;

import javax.persistence.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@SuperBuilder
@Table(name = "users")
@Entity
public class User extends BaseTimeEntity {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-z0-9._-]+@[a-z]+[.]+[a-z]{2,3}$");

    private static final int MAX_NICKNAME_LENGTH = 20;
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

    private  boolean activated;

    protected User() {
    }

    public User(String email, String nickname, String profileImageUrl) {
        validateEmail(email);
        validateNickname(nickname);
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

    private void validateNickname(final String nickname) {
        if(nickname.isEmpty() || nickname.length() > MAX_NICKNAME_LENGTH) {
            throw new InvalidUserException(String.format("이름은 1자 이상 %d자 이하여야 합니다.", MAX_NICKNAME_LENGTH));
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
    public boolean isActivated() {
        return activated;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }
    public void updateProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public void updateBan(int ban) {
        this.ban = ban;
    }
    public void updateActivated(boolean activated) {
        this.activated = activated;
    }

}
