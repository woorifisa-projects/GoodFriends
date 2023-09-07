package woorifisa.goodfriends.backend.profile.domain;

import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
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
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Column(name = "mobile_phone")
    private String mobilePhone;

    @Column(name = "address")
    private String address;

    @Column(name = "account_type")
    private AccountType accountType;

    @Column(name = "account_number")
    private int accountNumber;

    protected Profile() {
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

    public AccountType getAccountType() {
        return accountType;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void updateMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public void updateAddress(String address) {
        this.address = address;
    }

    public void updateAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
    public void updateAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }
}
