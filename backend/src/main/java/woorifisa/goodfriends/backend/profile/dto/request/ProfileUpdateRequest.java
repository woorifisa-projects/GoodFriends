package woorifisa.goodfriends.backend.profile.dto.request;

import woorifisa.goodfriends.backend.profile.domain.AccountType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProfileUpdateRequest {

    private String email;
    @NotBlank(message = "회원 닉네임이 공백일 수 없습니다.")
    private String nickName;
    @NotBlank(message = "회원 핸드폰 번호는 공백일 수 없습니다.")
    private String mobileNumber;
    @NotBlank(message = "회원 주소는 공백일 수 없습니다.")
    private String address;
    private AccountType accountType;
    @NotNull(message = "회원 계좌번호는 공백일 수 없습니다.")
    private String accountNumber;

    protected ProfileUpdateRequest() {
    }

    public ProfileUpdateRequest(String email, String nickName,
                                String mobileNumber, String address,
                                AccountType accountType, String accountNumber) {
        this.email = email;
        this.nickName = nickName;
        this.mobileNumber = mobileNumber;
        this.address = address;
        this.accountType = accountType;
        this.accountNumber = accountNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getNickName() {
        return nickName;
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
}
