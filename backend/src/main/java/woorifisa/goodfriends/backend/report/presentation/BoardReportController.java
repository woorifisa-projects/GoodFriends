package woorifisa.goodfriends.backend.report.presentation;

import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import woorifisa.goodfriends.backend.auth.dto.LoginUser;
import woorifisa.goodfriends.backend.auth.presentation.AuthenticationPrincipal;
import woorifisa.goodfriends.backend.report.application.ReportService;
import woorifisa.goodfriends.backend.report.dto.request.ReportSaveRequest;

import javax.validation.Valid;
import java.net.URI;

@RequestMapping("/api/report")
@RestController
public class BoardReportController {

    private final ReportService reportService;

    public BoardReportController(final ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    public ResponseEntity<Void> saveReport(@AuthenticationPrincipal LoginUser loginUser,
                                           @RequestParam final Long productId,
                                           @Valid @RequestBody final ReportSaveRequest request) {
        Long boardReportId = reportService.saveReport(loginUser, productId, request);
        return ResponseEntity.created(URI.create("/report/" + productId + "/" + boardReportId)).build();
    }
}
