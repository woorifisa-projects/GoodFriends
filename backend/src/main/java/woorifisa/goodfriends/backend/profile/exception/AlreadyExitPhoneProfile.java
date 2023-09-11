package woorifisa.goodfriends.backend.profile.exception;

public class AlreadyExitPhoneProfile extends RuntimeException{

    public AlreadyExitPhoneProfile(String message) {
        super(message);
    }

    public AlreadyExitPhoneProfile() {
        this("이미 존재하는 휴대폰 번호입니다.");
    }


}
