package woorifisa.goodfriends.backend.order.dto.response;

import lombok.Getter;

import java.util.List;

@Getter
public class OrdersProductResponse {

    private List<OrderProductResponse> responses;

    private boolean isDealStatus;

    public OrdersProductResponse(final List<OrderProductResponse> responses, final boolean isDealStatus) {
        this.responses = responses;
        this.isDealStatus = isDealStatus;
    }

}
