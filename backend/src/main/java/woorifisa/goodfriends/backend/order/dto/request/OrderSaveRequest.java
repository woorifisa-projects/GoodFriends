package woorifisa.goodfriends.backend.order.dto.request;

import lombok.Getter;

@Getter
public class OrderSaveRequest {

    private Long productId;

    private String possibleDateStart;

    private String possibleDateEnd;

    private String possibleTimeStart;

    private String possibleTimeEnd;

    private String requirements;

    public OrderSaveRequest() {
    }

    public OrderSaveRequest(Long productId, String possibleDateStart, String possibleDateEnd, String possibleTimeStart, String possibleTimeEnd, String requirements) {
        this.productId = productId;
        this.possibleDateStart = possibleDateStart;
        this.possibleDateEnd = possibleDateEnd;
        this.possibleTimeStart = possibleTimeStart;
        this.possibleTimeEnd = possibleTimeEnd;
        this.requirements = requirements;
    }

}
