package woorifisa.goodfriends.backend.report.dto.response;

import lombok.Getter;
import woorifisa.goodfriends.backend.report.domain.Report;
import woorifisa.goodfriends.backend.report.domain.ReportCategory;
import woorifisa.goodfriends.backend.report.domain.ReportStatus;
import java.time.LocalDateTime;

@Getter
public class ReportResponse {

    private Long id;
    private String email; // 피해자 이메일
    private String nickname; // 피해자 닉네임
    private String title; // 상품 이름
    private ReportCategory reportCategory; // 상품 카테고리
    private LocalDateTime createAt; // 신고날짜
    private ReportStatus reportStatus; // 처리상태

    public ReportResponse(String email, String nickname,
                               String title, ReportCategory reportCategory,
                               LocalDateTime createAt, ReportStatus reportStatus) {
        this.email = email;
        this.nickname = nickname;
        this.title = title;
        this.reportCategory = reportCategory;
        this.createAt = createAt;
        this.reportStatus = reportStatus;
    }

    public ReportResponse(Report report) {
        this.id =  report.getId();
        this.email = report.getUser().getEmail();
        this.nickname = report.getUser().getNickname();
        this.title = report.getProduct().getTitle();
        this.reportCategory = report.getReportCategory();
        this.createAt = report.getCreatedDate();
        this.reportStatus = report.getReportStatus();
    }
}