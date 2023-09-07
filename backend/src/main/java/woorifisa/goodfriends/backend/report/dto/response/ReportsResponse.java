package woorifisa.goodfriends.backend.report.dto.response;

import lombok.Getter;
import java.util.List;

@Getter
public class ReportsResponse {
    private final List<ReportResponse> declarations;

    public ReportsResponse(List<ReportResponse> declarations) {
        this.declarations = declarations;
    }
}