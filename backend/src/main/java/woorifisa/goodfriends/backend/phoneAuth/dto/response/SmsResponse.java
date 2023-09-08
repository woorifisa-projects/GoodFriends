package woorifisa.goodfriends.backend.phoneAuth.dto.response;


import lombok.Builder;
import java.time.LocalDateTime;


@Builder
public class SmsResponse {
    private String requestId;
    private LocalDateTime requestTime;
    private String statusCode;
    private String statusName;
    private String smsConfirmNum;

    public SmsResponse(String smsConfirmNum){
        this.smsConfirmNum = smsConfirmNum;
    }

    public SmsResponse(String requestId, LocalDateTime requestTime, String statusCode, String statusName, String smsConfirmNum) {
        this.requestId = requestId;
        this.requestTime = requestTime;
        this.statusCode = statusCode;
        this.statusName = statusName;
        this.smsConfirmNum = smsConfirmNum;
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

    public String getSmsConfirmNum() {
        return smsConfirmNum;
    }
}
