package woorifisa.goodfriends.backend.profile.dto.response;

import lombok.Builder;
import woorifisa.goodfriends.backend.profile.domain.AccountType;
import woorifisa.goodfriends.backend.profile.domain.Profile;
import woorifisa.goodfriends.backend.user.domain.User;

public class ProfileDetailResponse {
    private Long id;
    private String email;
    private String nickName;
    private String imageUrl;
    private String address;
    private String mobileNumber;
    private AccountType accountType;
    private String accountNumber;

    @Builder
    public ProfileDetailResponse(final Long id, final String email, final String nickName, final String imageUrl,
                                 final String address, final String mobileNumber, final AccountType accountType, final String accountNumber) {
        this.id = id;
        this.email = email;
        this.nickName = nickName;
        this.imageUrl = imageUrl;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.accountType = accountType;
        this.accountNumber = accountNumber;
    }

    public static ProfileDetailResponse of(final User user, final Profile profile) {
        if(profile == null) {
            return ProfileDetailResponse.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .nickName(user.getNickname())
                    .imageUrl(user.getProfileImageUrl())
                    .build();
        } else {
            return ProfileDetailResponse.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .nickName(user.getNickname())
                    .imageUrl(user.getProfileImageUrl())
                    .address(profile.getAddress())
                    .mobileNumber(profile.getMobileNumber())
                    .accountType(profile.getAccountType())
                    .accountNumber(profile.getAccountNumber())
                    .build();
        }
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNickName() {
        return nickName;
    }

    public String getImageUrl() {
        return imageUrl;
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
