package woorifisa.goodfriends.backend.order.dto.request;

public class OrderSaveRequest {

    private Long productId;

    private Long userId;

    private String possibleDateStart;

    private String possibleDateEnd;

    private String possibleTimeStart;

    private String possibleTimeEnd;

    private String requirements;

    public OrderSaveRequest() {
    }

    public OrderSaveRequest(Long productId, Long userId, String possibleDateStart, String possibleDateEnd, String possibleTimeStart, String possibleTimeEnd, String requirements) {
        this.productId = productId;
        this.userId = userId;
        this.possibleDateStart = possibleDateStart;
        this.possibleDateEnd = possibleDateEnd;
        this.possibleTimeStart = possibleTimeStart;
        this.possibleTimeEnd = possibleTimeEnd;
        this.requirements = requirements;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getPossibleDateStart() {
        return possibleDateStart;
    }

    public String getPossibleDateEnd() {
        return possibleDateEnd;
    }

    public String getPossibleTimeStart() {
        return possibleTimeStart;
    }

    public String getPossibleTimeEnd() {
        return possibleTimeEnd;
    }

    public String getRequirements() {
        return requirements;
    }

}
