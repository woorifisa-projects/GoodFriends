package woorifisa.goodfriends.backend.profile.dto.response;

public class ProfileViewResponse {
    private final Long id;
    private final String imageUrl;
    private final String nickName;
    private final String email;
    private final String address;
    private final String mobileNumber;

    public ProfileViewResponse(Long id, String imageUrl, String nickName, String email, String address, String mobileNumber) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.nickName = nickName;
        this.email = email;
        this.address = address;
        this.mobileNumber = mobileNumber;
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
}
