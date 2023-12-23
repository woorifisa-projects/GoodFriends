package woorifisa.goodfriends.backend.product.exception;

public class NotAccessProductException extends RuntimeException {
    public NotAccessProductException(String message) {
        super(message);
    }
    public NotAccessProductException() {
        this("비활성화 상태인 유저로 해당 페이지에 접근이 불가능합니다.");
    }
}