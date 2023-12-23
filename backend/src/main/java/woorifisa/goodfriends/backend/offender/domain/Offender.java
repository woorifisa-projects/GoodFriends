package woorifisa.goodfriends.backend.offender.domain;

import lombok.Builder;
import woorifisa.goodfriends.backend.common.BaseEntity;
import woorifisa.goodfriends.backend.user.domain.User;

import javax.persistence.*;

@Table(name = "offenders") // 부정행위자
@Entity
public class Offender extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true) // 외래 키로 사용할 컬럼 지정
    private User user;

    protected Offender() {
    }

    @Builder
    public Offender(User user) {
        this.user = user;
    }
    
    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }
}
