package woorifisa.goodfriends.backend.report.dto.request;

import lombok.Getter;
import woorifisa.goodfriends.backend.report.domain.Content;
import woorifisa.goodfriends.backend.report.domain.ReportCategory;

@Getter
public class ReportSaveRequest {

    private ReportCategory reportCategory;

    private Content content;

    protected ReportSaveRequest() {
    }

    public ReportSaveRequest(final ReportCategory reportCategory, final Content content) {
        this.reportCategory = reportCategory;
        this.content = content;
    }
}
