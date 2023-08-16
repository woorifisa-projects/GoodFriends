package woorifisa.goodfriends.backend.user.exception;

public class InvalidUserException extends RuntimeException {

    public InvalidUserException(final String message) {
        super();
    }

    public InvalidUserException() {
        this("잘못된 회원 정보입니다.");
    }
}
