package woorifisa.goodfriends.backend.product.exception;

public class NoSuchImageFileException extends RuntimeException {

    public NoSuchImageFileException(String message) {
        super(message);
    }

    public NoSuchImageFileException() {
        this("파일명을 변경할 이미지 파일이 존재하지 않습니다.");
    }
}
