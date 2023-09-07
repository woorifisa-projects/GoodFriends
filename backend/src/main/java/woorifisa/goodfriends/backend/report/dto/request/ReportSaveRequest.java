package woorifisa.goodfriends.backend.report.dto.request;

import lombok.Getter;
import woorifisa.goodfriends.backend.report.domain.Content;
import woorifisa.goodfriends.backend.report.domain.ReportCategory;

@Getter
public class ReportSaveRequest {

    private ReportCategory reportCategory;

    private Content content;

    public ReportSaveRequest(ReportCategory reportCategory, Content content) {
        this.reportCategory = reportCategory;
        this.content = content;
    }
}
