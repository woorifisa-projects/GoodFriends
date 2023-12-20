package woorifisa.goodfriends.backend.report.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReportTest {
    @DisplayName("신고 내용을 입력하지 않은 경우 예외가 발생한다")
    @Test
    void reportContent() {
        // given
        String reportContent = null;

        // when, then
        Assertions.assertThatThrownBy(() -> new Content(reportContent))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("신고 내용은 null일 수 없습니다.");
    }
}