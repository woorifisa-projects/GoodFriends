package woorifisa.goodfriends.backend.order.dto.response;

public class OrderViewAllResponse {

    private Long id;

    private String nickName;

    private String possibleDate;

    private String possibleTime;

    public OrderViewAllResponse() {
    }

    public OrderViewAllResponse(Long id, String nickName, String possibleDate, String possibleTime) {
        this.id = id;
        this.nickName = nickName;
        this.possibleDate = possibleDate;
        this.possibleTime = possibleTime;
    }

    public Long getId() {
        return id;
    }

    public String getNickName() {
        return nickName;
    }

    public String getPossibleDate() {
        return possibleDate;
    }

    public String getPossibleTime() {
        return possibleTime;
    }
}
