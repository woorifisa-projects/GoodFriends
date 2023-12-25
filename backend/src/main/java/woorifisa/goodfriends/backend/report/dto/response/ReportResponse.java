package woorifisa.goodfriends.backend.report.dto.response;

import lombok.Getter;
import woorifisa.goodfriends.backend.report.domain.Report;
import woorifisa.goodfriends.backend.report.domain.ReportCategory;
import woorifisa.goodfriends.backend.report.domain.ReportStatus;
import java.time.LocalDateTime;

@Getter
public class ReportResponse {

    private Long id;
    private String email;
    private String nickname;
    private String title;
    private ReportCategory reportCategory;
    private LocalDateTime createAt;
    private ReportStatus reportStatus;

    public ReportResponse(final String email, final String nickname,
                               final String title, final ReportCategory reportCategory,
                               final LocalDateTime createAt, final ReportStatus reportStatus) {
        this.email = email;
        this.nickname = nickname;
        this.title = title;
        this.reportCategory = reportCategory;
        this.createAt = createAt;
        this.reportStatus = reportStatus;
    }

    public ReportResponse(final Report report) {
        this.id =  report.getId();
        this.email = report.getUser().getEmail();
        this.nickname = report.getUser().getNickname();
        this.title = report.getProduct().getTitle();
        this.reportCategory = report.getReportCategory();
        this.createAt = report.getCreatedAt();
        this.reportStatus = report.getReportStatus();
    }
}