package woorifisa.goodfriends.backend.report.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReportStatus {
    PROCESSING("처리중"),
    COMPLETED("처리 완료");
    private final String text;
}
