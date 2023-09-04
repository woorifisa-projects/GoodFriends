package woorifisa.goodfriends.backend.auth.exception;


public class EmptyAuthorizationHeaderException extends RuntimeException{

    public EmptyAuthorizationHeaderException(final String message) {
        super();
    }
    public EmptyAuthorizationHeaderException() {
        this("Header에 Authorization이 존재하지 않습니다.");
    }
}
