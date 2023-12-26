package woorifisa.goodfriends.backend.product.exception;

public class InactiveUserAccessException extends RuntimeException {
    public InactiveUserAccessException(String message) {
        super(message);
    }

    public InactiveUserAccessException() {
        this("비활성화 상태인 유저로 해당 페이지에 접근이 불가능합니다.");
    }
}