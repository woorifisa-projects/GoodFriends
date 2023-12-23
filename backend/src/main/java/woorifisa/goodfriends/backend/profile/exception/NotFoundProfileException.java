package woorifisa.goodfriends.backend.profile.exception;

public class NotFoundProfileException extends RuntimeException{

    public NotFoundProfileException(String message) {
        super(message);
    }

    public NotFoundProfileException() {
        this("프로필을 등록하지 않은 유저입니다.");
    }


}
