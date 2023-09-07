package woorifisa.goodfriends.backend.order.dto.response;

import lombok.Getter;

import java.util.List;

@Getter
public class OrderViewAllResponse {

    List<OrderViewOneResponse> responses;

    public OrderViewAllResponse(List<OrderViewOneResponse> responses) {
        this.responses = responses;
    }

}
