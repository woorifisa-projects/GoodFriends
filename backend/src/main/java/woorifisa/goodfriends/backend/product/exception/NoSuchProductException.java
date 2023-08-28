package woorifisa.goodfriends.backend.product.exception;

public class NoSuchProductException extends RuntimeException {

    public NoSuchProductException(String message) {
        super(message);
    }

    public NoSuchProductException() {
        this("존재하지 않는 물품입니다.");
    }
}
