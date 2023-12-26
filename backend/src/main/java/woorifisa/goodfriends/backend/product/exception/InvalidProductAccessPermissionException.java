package woorifisa.goodfriends.backend.product.exception;

public class InvalidProductAccessPermissionException extends RuntimeException {

    public InvalidProductAccessPermissionException(String message) {
        super(message);
    }

    public InvalidProductAccessPermissionException() {
        this("본인이 작성한 판매글만 수정/삭제 가능합니다.");
    }
}
