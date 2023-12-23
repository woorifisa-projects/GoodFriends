package woorifisa.goodfriends.backend.profile.dto.response;


public class ProfileUpdateResponse {

    private String email;
    private String nickName;
    private String profileImageUrl;
    private String mobileNumber;
    private String address;

    public ProfileUpdateResponse(final String email, final String nickName, final String profileImageUrl, final String mobileNumber, final String address) {
        this.email = email;
        this.nickName = nickName;
        this.profileImageUrl = profileImageUrl;
        this.mobileNumber = mobileNumber;
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public String getNickName() {
        return nickName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getAddress() {
        return address;
    }
}
