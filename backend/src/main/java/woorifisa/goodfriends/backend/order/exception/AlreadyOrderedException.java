package woorifisa.goodfriends.backend.order.exception;

public class AlreadyOrderedException extends RuntimeException{
    public AlreadyOrderedException(String message) {
        super(message);
    }

    public AlreadyOrderedException() {
        this("이미 주문서를 보낸 상품입니다.");
    }
}
