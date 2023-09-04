package woorifisa.goodfriends.backend.product.exception;

public class NotFoundImageFileException extends RuntimeException {

    public NotFoundImageFileException(String message) {
        super(message);
    }

    public NotFoundImageFileException() {
        this("파일명을 변경할 이미지 파일이 존재하지 않습니다.");
    }
}
