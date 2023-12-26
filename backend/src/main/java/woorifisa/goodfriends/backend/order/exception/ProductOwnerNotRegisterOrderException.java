package woorifisa.goodfriends.backend.order.exception;

public class ProductOwnerNotRegisterOrderException extends RuntimeException {
    public ProductOwnerNotRegisterOrderException(String message) {
        super(message);
    }

    public ProductOwnerNotRegisterOrderException() {
        this("본인이 등록한 상품은 주문서 등록이 불가합니다.");
    }
}
