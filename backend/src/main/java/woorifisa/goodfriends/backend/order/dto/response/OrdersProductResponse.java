package woorifisa.goodfriends.backend.order.dto.response;

import lombok.Getter;

import java.util.List;

@Getter
public class OrdersProductResponse {

    private List<OrderProductResponse> responses;

    private boolean isDealStatus; // 거래시작 여부

    public OrdersProductResponse(final List<OrderProductResponse> responses, final boolean isDealStatus) {
        this.responses = responses;
        this.isDealStatus = isDealStatus;
    }

}
