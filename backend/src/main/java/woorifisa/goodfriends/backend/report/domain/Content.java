package woorifisa.goodfriends.backend.report.domain;


import lombok.EqualsAndHashCode;
import woorifisa.goodfriends.backend.report.exception.ReportException;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;
import java.util.Objects;

@Embeddable
@EqualsAndHashCode
public class Content {

    public static final int MAX_LENGTH = 300;

    @Column(name = "content", nullable = false)
    @Lob
    private String value;

    protected Content() {
    }

    public Content(final String value) {
        validate(value);
        this.value = value;
    }
    private void validate(final String value) {
        if (Objects.isNull(value)) {
            throw new NullPointerException("신고 내용은 null일 수 없습니다.");
        }
        if (value.length() > MAX_LENGTH) {
            throw new ReportException.ContentLengthException(MAX_LENGTH, value);
        }
    }

    public String getValue() {
        return value;
    }
}
