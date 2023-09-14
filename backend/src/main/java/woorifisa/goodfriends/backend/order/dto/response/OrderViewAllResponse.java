package woorifisa.goodfriends.backend.order.dto.response;

import lombok.Getter;

import java.util.List;

@Getter
public class OrderViewAllResponse {

    private List<OrderViewOneResponse> responses;

    private boolean dealStatus;

    public OrderViewAllResponse(List<OrderViewOneResponse> responses, boolean dealStatus) {
        this.responses = responses;
        this.dealStatus = dealStatus;
    }

}
