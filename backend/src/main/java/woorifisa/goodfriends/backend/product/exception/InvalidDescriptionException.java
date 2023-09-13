package woorifisa.goodfriends.backend.product.exception;

public class InvalidDescriptionException extends RuntimeException {

    public InvalidDescriptionException(String message) {
        super(message);
    }

    public InvalidDescriptionException() {
        this("상세 설명을 10글자 이상 입력해주세요.");
    }
}
