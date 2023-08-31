package woorifisa.goodfriends.backend.order.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class AlreadyOrderedException extends RuntimeException{
    public AlreadyOrderedException(String message) {
        super(message);
    }

    public AlreadyOrderedException() {
        this("이미 주문서를 보낸 상품입니다.");
    }
}
