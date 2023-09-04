package woorifisa.goodfriends.backend.user.exception;

public class NotFoundUserException extends RuntimeException {

    public NotFoundUserException(final String message) {
        super();
    }

    public NotFoundUserException() {
        this("존재하지 않는 유저입니다.");
    }
}
