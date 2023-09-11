package woorifisa.goodfriends.backend.profile.exception;

public class NotFoundProfile extends RuntimeException{

    public NotFoundProfile(String message) {
        super(message);
    }

    public NotFoundProfile() {
        this("프로필을 등록하지 않은 유저입니다.");
    }


}
