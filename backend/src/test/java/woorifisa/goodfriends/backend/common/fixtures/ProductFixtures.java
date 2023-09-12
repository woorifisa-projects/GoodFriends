package woorifisa.goodfriends.backend.common.fixtures;

import woorifisa.goodfriends.backend.product.domain.Product;
import woorifisa.goodfriends.backend.product.domain.ProductCategory;
import woorifisa.goodfriends.backend.product.domain.ProductStatus;
import woorifisa.goodfriends.backend.user.domain.User;

public class ProductFixtures {

    // 상품 제목
    public static final String 제목1 = "아이패드";
    public static final String 제목2 = "티셔츠";
    public static final String 제목3 = "화분";

    //상품 상세 설명
    public static final String 상세_설명1 = "최신형 아이패드 팔아요.";
    public static final String 상세_설명2 = "실착 1회 티셔츠 입니다.";
    public static final String 상세_설명3 = "다육이 화분 1개 있습니다.";

    // 판매 가격
    public static final int 판매_가격1 = 100000;
    public static final int 판매_가격2 = 20000;
    public static final int 판매_가격3 = 5000;


    public static Product 상품1() {
        return Product.builder()
                .user(new User("goodfriends@gmail.com", "굿프렌즈", "image.png"))
                .productCategory(ProductCategory.DIGITAL_DEVICE)
                .title(제목1)
                .status(ProductStatus.SELL)
                .description(상세_설명1)
                .sellPrice(판매_가격1)
                .build();
    }

    public static Product 상품2() {
        return Product.builder()
                .user(new User("goodfriends@gmail.com", "굿프렌즈", "image.png"))
                .productCategory(ProductCategory.CLOTHING)
                .title(제목2)
                .status(ProductStatus.SELL)
                .description(상세_설명2)
                .sellPrice(판매_가격2)
                .build();
    }

    public static Product 상품3() {
        return Product.builder()
                .user(new User("goodfriends@gmail.com", "굿프렌즈", "image.png"))
                .productCategory(ProductCategory.PLANTS)
                .title(제목3)
                .status(ProductStatus.SELL)
                .description(상세_설명3)
                .sellPrice(판매_가격3)
                .build();
    }
}