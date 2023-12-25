package woorifisa.goodfriends.backend.product.domain;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductCategory {
    BEAUTY("뷰티/미용"),
    PLANTS("식물"),
    PET_GOODS("반려동물용품"),
    EXCHANGE_TICKET("티켓/교환권"),
    BOOK("도서"),
    DIGITAL_DEVICE("디지털기기"),
    FURNITURE("가구/인테리어"),
    BABY_GOODS("유아동"),
    CLOTHING("의류"),
    PROCESSED_FOOD("가공식품"),
    LIVING("생활/주방"),
    SPORTS("스포츠/레저"),
    HOBBY("취미/게임/음반"),
    ETC("기타");

    private final String text;
}
