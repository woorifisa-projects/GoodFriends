package woorifisa.goodfriends.backend.user.exception;

public class NoSuchUserException extends RuntimeException {

    public NoSuchUserException(final String message) {
        super();
    }

    public NoSuchUserException() {
        this("존재하지 않는 유저입니다.");
    }
}
