package woorifisa.goodfriends.backend.product.exception;

public class NotFoundProductException extends RuntimeException {

    public NotFoundProductException(String message) {
        super(message);
    }

    public NotFoundProductException() {
        this("존재하지 않는 물품입니다.");
    }
}
