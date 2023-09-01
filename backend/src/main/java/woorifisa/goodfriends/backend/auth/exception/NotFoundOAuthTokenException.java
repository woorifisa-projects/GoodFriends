package woorifisa.goodfriends.backend.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class NotFoundOAuthTokenException extends RuntimeException {

    public NotFoundOAuthTokenException(final String message) {
        super(message);
    }
    public NotFoundOAuthTokenException() {
        this("존재하지 않는 OAuthToken 입니다.");
    }
}
