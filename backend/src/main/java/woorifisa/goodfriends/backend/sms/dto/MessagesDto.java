package woorifisa.goodfriends.backend.sms.dto;


import lombok.Builder;

@Builder
public class MessagesDto {
    private String to;
    private String subject;
    private String content;

    public MessagesDto(String to, String subject, String content) { //받는사람, 제목, 내용
        this.to = to;
        this.subject = subject;
        this.content = content;
    }

    public String getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }
}
