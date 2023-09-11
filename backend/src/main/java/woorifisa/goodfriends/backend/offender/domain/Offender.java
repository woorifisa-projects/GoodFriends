package woorifisa.goodfriends.backend.offender.domain;

import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import woorifisa.goodfriends.backend.global.common.BaseCreateTimeEntity;
import woorifisa.goodfriends.backend.user.domain.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@SuperBuilder
@Table(name = "offenders") // 부정행위자
@Entity
public class Offender extends BaseCreateTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true) // 외래 키로 사용할 컬럼 지정
    private User user;

    protected Offender() {
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }
}
