package woorifisa.goodfriends.backend.profile.exception;

public class AlreadyExitPhoneProfileException extends RuntimeException{

    public AlreadyExitPhoneProfileException(String message) {
        super(message);
    }

    public AlreadyExitPhoneProfileException() {
        this("이미 존재하는 휴대폰 번호입니다.");
    }


}
