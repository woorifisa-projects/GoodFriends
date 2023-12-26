package woorifisa.goodfriends.backend.order.exception;

public class InvalidProductOrderAccessException extends RuntimeException {
    public InvalidProductOrderAccessException(String message) {
        super(message);
    }

    public InvalidProductOrderAccessException() {
        this("본인이 등록하지 않은 상품의 주문서는 조회할 수 없습니다.");
    }
}
