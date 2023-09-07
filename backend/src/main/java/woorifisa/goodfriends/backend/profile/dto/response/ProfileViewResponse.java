package woorifisa.goodfriends.backend.profile.dto.response;

import woorifisa.goodfriends.backend.profile.domain.AccountType;

public class ProfileViewResponse {
    private final Long id;
    private final String imageUrl;
    private final String nickName;
    private final String email;
    private final String address;
    private final String mobileNumber;
    private final AccountType accountType;
    private final String accountNumber;

    public ProfileViewResponse(Long id, String imageUrl, String nickName,
                               String email, String address, String mobileNumber,
                               AccountType accountType, String accountNumber) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.nickName = nickName;
        this.email = email;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.accountType = accountType;
        this.accountNumber = accountNumber;
    }

    public Long getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }
    public String getMobileNumber() {
        return mobileNumber;
    }
    public AccountType getAccountType() {
        return accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
