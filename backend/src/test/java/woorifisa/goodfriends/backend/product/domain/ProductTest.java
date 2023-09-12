package woorifisa.goodfriends.backend.product.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import woorifisa.goodfriends.backend.product.exception.InvalidDescriptionException;
import woorifisa.goodfriends.backend.user.domain.User;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @DisplayName("상품을 생성한다.")
    @Test
    void 상품을_생성한다() {
        assertDoesNotThrow(() -> Product.builder()
                .user(new User("goodfriends@gmail.com", "굿프렌즈", "image.png"))
                .productCategory(ProductCategory.DIGITAL_DEVICE)
                .title("아이패드")
                .status(ProductStatus.SELL)
                .description("아이패드입니다.")
                .sellPrice(100000)
                .build());
    }



}