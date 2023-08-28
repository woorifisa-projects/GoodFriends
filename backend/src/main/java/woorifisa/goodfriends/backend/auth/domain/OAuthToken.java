package woorifisa.goodfriends.backend.auth.domain;

import woorifisa.goodfriends.backend.user.domain.User;

import javax.persistence.*;

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

    public OAuthToken() {
    }

    public OAuthToken(User user) {
        this.user = user;
    }

    public OAuthToken(Long id) {
        this.id = id;
    }
}
