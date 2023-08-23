package woorifisa.goodfriends.backend.infrastructure.oauth.dto;

// 구글 OAuth 서비스로부터 받은 사용자 프로필 정보를 나타내는 클래스
// 이 클래스는 사용자의 이메일, 이름, 프로필 사진 URL 등의 정보를 저장하고 관리하기 위해 사용됩니다.
public class UserInfo {

    private String email;
    private String name;

    private String picture;

    private UserInfo() {
    }

    public UserInfo(String email, String name, String picture) {
        this.email = email;
        this.name = name;
        this.picture = picture;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPicture() {
        return picture;
    }
}
