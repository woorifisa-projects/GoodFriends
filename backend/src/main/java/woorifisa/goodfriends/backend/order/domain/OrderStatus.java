package woorifisa.goodfriends.backend.order.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {
    ORDERING("주문중"),
    RESERVATION("예약중"),
    COMPLETED("주문완료");

    private final String text;
}
