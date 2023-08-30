package woorifisa.goodfriends.backend.order.dto.response;

public class OrderViewAllResponse {

    private Long id;

    private String nickName;

    private String possibleDate;

    private String possibleTime;

    private String requirements;

    public OrderViewAllResponse() {
    }

    public OrderViewAllResponse(Long id, String nickName, String possibleDate, String possibleTime, String requirements) {
        this.id = id;
        this.nickName = nickName;
        this.possibleDate = possibleDate;
        this.possibleTime = possibleTime;
        this.requirements = requirements;
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

    public String getRequirements() {
        return requirements;
    }

}
