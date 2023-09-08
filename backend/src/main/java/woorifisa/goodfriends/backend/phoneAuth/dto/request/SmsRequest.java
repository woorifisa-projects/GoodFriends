package woorifisa.goodfriends.backend.phoneAuth.dto.request;


import lombok.Builder;
import woorifisa.goodfriends.backend.phoneAuth.dto.MessageDto;
import java.util.List;


@Builder
public class SmsRequest {

    private String type;
    private String contentType;
    private String countryCode;
    private String from;
    private String content;
    private List<MessageDto> message;

    public SmsRequest(String type, String contentType, String countryCode, String from, String content, List<MessageDto> message) {
        this.type = type;
        this.contentType = contentType;
        this.countryCode = countryCode;
        this.from = from;
        this.content = content;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public String getContentType() {
        return contentType;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getFrom() {
        return from;
    }

    public String getContent() {
        return content;
    }

    public List<MessageDto> getMessage() {
        return message;
    }
}
