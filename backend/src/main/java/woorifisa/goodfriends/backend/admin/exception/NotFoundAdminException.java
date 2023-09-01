package woorifisa.goodfriends.backend.admin.exception;

public class NotFoundAdminException extends RuntimeException {

    public NotFoundAdminException(String message) {
        super(message);
    }
    public NotFoundAdminException() {
        this("존재하지 않는 어드민 id 입니다.");
    }
}
