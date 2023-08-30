package woorifisa.goodfriends.backend.profile.domain;

import lombok.Builder;
import lombok.experimental.SuperBuilder;
import woorifisa.goodfriends.backend.global.common.BaseCreateTimeEntity;
import woorifisa.goodfriends.backend.user.domain.User;

import javax.persistence.*;

@SuperBuilder
@Table(name = "profiles")
@Entity
public class Profile extends BaseCreateTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private User user;

    @Column(name = "mobile_phone")
    private String mobilePhone;

    @Column(name = "address")
    private String address;

    protected Profile() {
    }

    @Builder
    public Profile(User user, String mobilePhone, String address) {
        this.user = user;
        this.mobilePhone = mobilePhone;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getAddress() {
        return address;
    }
}
