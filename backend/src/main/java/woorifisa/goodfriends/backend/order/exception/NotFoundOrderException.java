package woorifisa.goodfriends.backend.order.exception;

public class NotFoundOrderException extends RuntimeException{
    public NotFoundOrderException(String message) {
        super(message);
    }

    public NotFoundOrderException() {
        this("주문서가 존재하지 않습니다.");
    }
}
