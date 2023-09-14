package woorifisa.goodfriends.backend.order.exception;

public class OwnProductException extends RuntimeException {
    public OwnProductException(String message) {
        super(message);
    }

    public OwnProductException() {
        this("본인이 등록한 상품은 주문서 등록이 불가합니다.");
    }
}
