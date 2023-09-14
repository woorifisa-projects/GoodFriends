package woorifisa.goodfriends.backend.order.exception;

public class NotOwnProductException extends RuntimeException {
    public NotOwnProductException(String message) {
        super(message);
    }

    public NotOwnProductException() {
        this("본인이 등록하지 않은 상품의 주문서는 조회할 수 없습니다.");
    }
}
