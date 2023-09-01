package woorifisa.goodfriends.backend.profile.dto.response;

public class ProfileViewResponse {

    private final Long id;
    private final String imageUrl;
    private final String nickname;
    private final String email;
    private final String address;
    private final String phoneNumber;

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
