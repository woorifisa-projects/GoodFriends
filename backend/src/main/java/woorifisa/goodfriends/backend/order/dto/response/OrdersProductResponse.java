package woorifisa.goodfriends.backend.order.dto.response;

import lombok.Getter;

import java.util.List;

@Getter
public class OrdersProductResponse {

    private List<OrderProductResponse> responses;

    private boolean dealStatus;

    public OrdersProductResponse(final List<OrderProductResponse> responses, final boolean dealStatus) {
        this.responses = responses;
        this.dealStatus = dealStatus;
    }

}
