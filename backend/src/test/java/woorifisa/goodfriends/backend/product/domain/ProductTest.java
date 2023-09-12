package woorifisa.goodfriends.backend.product.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import woorifisa.goodfriends.backend.product.exception.InvalidDescriptionException;
import woorifisa.goodfriends.backend.user.domain.User;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static woorifisa.goodfriends.backend.common.fixtures.ProductFixtures.*;

class ProductTest {

    @DisplayName("상품을 생성한다.")
    @Test
    void 상품을_생성한다() {
        assertDoesNotThrow(() -> Product.builder()
                .user(new User("goodfriends@gmail.com", "굿프렌즈", "image.png"))
                .productCategory(ProductCategory.DIGITAL_DEVICE)
                .title(제목1)
                .status(ProductStatus.SELL)
                .description(상세_설명1)
                .sellPrice(판매_가격1)
                .build());
    }


    @DisplayName("상세 설명이 10글자 미만이면 에러를 던진다.")
    @ParameterizedTest
    @ValueSource(strings = {"일", "일이", "일이삼", "일이삼사", "일이삼사오", "일이삼사오육", "일이삼사오육칠", "일이삼사오육칠팔", "일이삼사오육칠팔구"})
    void 상세_설명이_10글자_미만이면_에러를_던진다(final String description) {
        assertThrows(InvalidDescriptionException.class, () -> {
            Product product = Product.builder()
                    .user(new User("goodfriends@gmail.com", "굿프렌즈", "image.png"))
                    .productCategory(ProductCategory.DIGITAL_DEVICE)
                    .title(제목1)
                    .status(ProductStatus.SELL)
                    .description(description)
                    .sellPrice(판매_가격1)
                    .build();

            product.validDescription(product.getDescription());
        });
    }
}