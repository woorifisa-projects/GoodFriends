package woorifisa.goodfriends.backend.report.exception;

public class AlreadyReportedException extends RuntimeException {

    public AlreadyReportedException(String message) {
        super(message);
    }
    public AlreadyReportedException() {
        this("이미 신고한 유저는 중복 신고할 수 없습니다.");
    }
}
