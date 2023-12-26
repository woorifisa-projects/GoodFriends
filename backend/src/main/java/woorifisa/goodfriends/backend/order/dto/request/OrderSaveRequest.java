package woorifisa.goodfriends.backend.order.dto.request;

import lombok.Builder;
import lombok.Getter;
import woorifisa.goodfriends.backend.order.domain.Order;
import woorifisa.goodfriends.backend.order.domain.OrderStatus;
import woorifisa.goodfriends.backend.product.domain.Product;
import woorifisa.goodfriends.backend.user.domain.User;

@Getter
public class OrderSaveRequest {

    private Long productId;

    private String possibleDateStart;

    private String possibleDateEnd;

    private String possibleTimeStart;

    private String possibleTimeEnd;

    private String requirements;

    protected OrderSaveRequest() {
    }

    @Builder
    public OrderSaveRequest(final Long productId, final String possibleDateStart, final String possibleDateEnd, final String possibleTimeStart, final String possibleTimeEnd, final String requirements) {
        this.productId = productId;
        this.possibleDateStart = possibleDateStart;
        this.possibleDateEnd = possibleDateEnd;
        this.possibleTimeStart = possibleTimeStart;
        this.possibleTimeEnd = possibleTimeEnd;
        this.requirements = requirements;
    }

    public Order toEntity(final Product foundProduct, final User foundUser, final OrderSaveRequest request) {
        return Order.builder()
                .product(foundProduct)
                .user(foundUser)
                .orderStatus(OrderStatus.ORDERING)
                .possibleDate(request.getPossibleDateStart() + " ~ " + request.getPossibleDateEnd())
                .possibleTime(request.getPossibleTimeStart() + " ~ " + request.getPossibleTimeEnd())
                .requirements(request.getRequirements())
                .build();
    }
}
