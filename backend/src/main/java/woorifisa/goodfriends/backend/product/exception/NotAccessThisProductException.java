package woorifisa.goodfriends.backend.product.exception;

public class NotAccessThisProductException extends RuntimeException {

    public NotAccessThisProductException(String message) {
        super(message);
    }

    public NotAccessThisProductException() {
        this("본인이 작성한 판매글만 수정/삭제 가능합니다.");
    }
}
