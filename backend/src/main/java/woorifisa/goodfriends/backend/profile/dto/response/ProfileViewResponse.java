package woorifisa.goodfriends.backend.profile.dto.response;

public class ProfileViewResponse {

    // id, profile image, nickname, email, address

    private Long id;
    private String imageUrl;
    private String nickname;
    private String email;
    private String address;
    private String phoneNumber;

    public ProfileViewResponse(){}

    public ProfileViewResponse(Long id, String imageUrl, String nickname, String email, String address, String phoneNumber) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.nickname = nickname;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
