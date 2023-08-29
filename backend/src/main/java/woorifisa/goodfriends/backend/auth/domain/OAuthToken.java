package woorifisa.goodfriends.backend.auth.domain;

import woorifisa.goodfriends.backend.user.domain.User;

import javax.persistence.*;
import java.util.Objects;

// 유저 개인별 토큰을 저장하는 엔티티
@Table(name = "oauth_tokens")
@Entity
public class OAuthToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "refresh_token")
    private String refreshToken;

    protected OAuthToken() {
    }

    public OAuthToken(User user, String refreshToken) {
        this.user = user;
        this.refreshToken = refreshToken;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void change(final String refreshToken) {
        if(!Objects.isNull(refreshToken)) {
            this.refreshToken = refreshToken;
        }
    }
}
