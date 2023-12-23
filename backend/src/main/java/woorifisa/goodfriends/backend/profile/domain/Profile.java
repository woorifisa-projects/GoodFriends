package woorifisa.goodfriends.backend.profile.domain;

import lombok.Builder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import woorifisa.goodfriends.backend.common.BaseEntity;
import woorifisa.goodfriends.backend.user.domain.User;
import woorifisa.goodfriends.backend.user.exception.InvalidUserException;

import javax.persistence.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Table(name = "profiles")
@Entity
public class Profile extends BaseEntity {

    private static final Pattern MOBILE_NUMBER = Pattern.compile("^\\d{3}-\\d{3,4}-\\d{4}$");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Column(name = "mobile_phone")
    private String mobileNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "account_type")
    private AccountType accountType;

    @Column(name = "account_number")
    private String accountNumber;

    protected Profile() {
    }

    @Builder
    public Profile(final User user, final String mobileNumber,
                   final String address, final AccountType accountType,
                   final String accountNumber) {
        validateMobileNumber(mobileNumber);
        
        this.user = user;
        this.mobileNumber = mobileNumber;
        this.address = address;
        this.accountType = accountType;
        this.accountNumber = accountNumber;
    }

    private void validateMobileNumber(final String mobileNumber) {
        Matcher matcher = MOBILE_NUMBER.matcher(mobileNumber);
        if(mobileNumber.isEmpty() || !matcher.matches()) {
            throw new InvalidUserException("핸드폰 형식이 올바르지 않습니다.");
        }
    }

    public Long getId() {
        return id;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void updateMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void updateAddress(String address) {
        this.address = address;
    }

    public void updateAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
    public void updateAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
