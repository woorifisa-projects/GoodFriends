package woorifisa.goodfriends.backend.report.presentation;

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
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/{productId}")
    public ResponseEntity<Void> saveReport(@AuthenticationPrincipal LoginUser loginUser,
                                           @PathVariable Long productId,
                                           @Valid @RequestBody ReportSaveRequest request) {
        Long declarationId = reportService.saveReport(loginUser, productId, request);
        return ResponseEntity.created(URI.create("/report/" + declarationId)).build();
    }
}
