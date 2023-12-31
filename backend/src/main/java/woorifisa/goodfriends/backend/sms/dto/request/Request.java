package woorifisa.goodfriends.backend.sms.dto.request;


public class Request {

    private String recipientPhoneNumber;
    private String title;
    private String content;

    public Request(String recipientPhoneNumber, String title, String content) {
        this.recipientPhoneNumber = recipientPhoneNumber;
        this.title = title;
        this.content = content;
    }

    public String getRecipientPhoneNumber() {
        return recipientPhoneNumber;
    }

    public String getTitle() {
        return title;
    }
    public String getContent(){
        return content;
    }

}
