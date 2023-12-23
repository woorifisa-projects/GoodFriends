package woorifisa.goodfriends.backend.product.domain;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductStatus {
    SELL("판매중"), // 기본값
    RESERVATION("예약중"),
    COMPLETED("거래완료");

    private final String text;
}
