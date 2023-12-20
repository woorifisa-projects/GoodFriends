package woorifisa.goodfriends.backend.report.dto.request;

import lombok.Getter;
import woorifisa.goodfriends.backend.product.domain.Product;
import woorifisa.goodfriends.backend.report.domain.Content;
import woorifisa.goodfriends.backend.report.domain.Report;
import woorifisa.goodfriends.backend.report.domain.ReportCategory;
import woorifisa.goodfriends.backend.report.domain.ReportStatus;
import woorifisa.goodfriends.backend.user.domain.User;

@Getter
public class ReportCreateServiceRequest {

    private ReportCategory reportCategory;

    private String content;

    protected ReportCreateServiceRequest() {
    }

    public ReportCreateServiceRequest(final ReportCategory reportCategory, final String content) {
        this.reportCategory = reportCategory;
        this.content = content;
    }

    public Report toEntity(final User user, final Product product) {
        return Report.builder()
                .reportCategory(reportCategory)
                .content(new Content(content))
                .reportStatus(ReportStatus.PROCESSING)
                .user(user)
                .product(product)
                .build();
    }
}
