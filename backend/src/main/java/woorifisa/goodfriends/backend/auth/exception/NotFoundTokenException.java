package woorifisa.goodfriends.backend.auth.exception;


public class NotFoundTokenException extends RuntimeException {
    public NotFoundTokenException(final String message) {
        super(message);
    }
    public NotFoundTokenException() {
        this("존재하지 않는 Token 입니다.");
    }
}
