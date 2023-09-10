package woorifisa.goodfriends.backend.phoneAuth.dto.response;


import lombok.Builder;
import java.time.LocalDateTime;


@Builder
public class SmsResponse {
    private String requestId;
    private LocalDateTime requestTime;
    private String statusCode;
    private String statusName;
    public SmsResponse() {

    }


    public SmsResponse(String requestId, LocalDateTime requestTime, String statusCode, String statusName) {
        this.requestId = requestId;
        this.requestTime = requestTime;
        this.statusCode = statusCode;
        this.statusName = statusName;
    }

    public String getRequestId() {
        return requestId;
    }

    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getStatusName() {
        return statusName;
    }
}
