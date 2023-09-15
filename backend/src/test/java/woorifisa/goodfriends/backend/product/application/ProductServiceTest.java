package woorifisa.goodfriends.backend.product.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import woorifisa.goodfriends.backend.global.application.S3Service;
import woorifisa.goodfriends.backend.offender.domain.OffenderRepository;
import woorifisa.goodfriends.backend.product.domain.Product;
import woorifisa.goodfriends.backend.product.domain.ProductImageRepository;
import woorifisa.goodfriends.backend.product.domain.ProductRepository;
import woorifisa.goodfriends.backend.product.dto.response.ProductViewsAllResponse;
import woorifisa.goodfriends.backend.profile.domain.ProfileRepository;
import woorifisa.goodfriends.backend.user.domain.UserRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static woorifisa.goodfriends.backend.common.fixtures.ProductFixtures.*;
import static woorifisa.goodfriends.backend.common.fixtures.ProfileFixtures.*;
import static woorifisa.goodfriends.backend.common.fixtures.UserFixtures.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    ProductService productService;

    @Mock
    ProductRepository productRepository;

    @Mock
    ProductImageRepository productImageRepository;

    @Mock
    ProfileRepository profileRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    OffenderRepository offenderRepository;

    @Mock
    S3Service s3Service;

    @Test
    void saveProduct() {
    }

    @Test
    void existOffender() {
    }

    @Test
    void existProfile() {
    }

    @Test
    void viewSearchProduct() {
    }

    @Test
    void viewProductByCategory() {
    }

    @DisplayName("전체 상품을 조회한다.")
    @Test
    void 전체_상품을_조회한다() {
        List<Product> products = new ArrayList<>();
        products.add(상품3());
        products.add(상품2());
        products.add(상품1());

        Pageable pageable = PageRequest.of(0,1);

        given(productRepository.findAllOrderByIdDesc(pageable)).willReturn(products);

        given(productImageRepository.findOneImageUrlByProductId(상품1().getId())).willReturn("image1.png");
        given(productImageRepository.findOneImageUrlByProductId(상품2().getId())).willReturn("image2.png");
        given(productImageRepository.findOneImageUrlByProductId(상품3().getId())).willReturn("image3.png");

        given(userRepository.getById(상품1().getUser().getId())).willReturn(코코());
        given(userRepository.getById(상품2().getUser().getId())).willReturn(춘식());
        given(userRepository.getById(상품3().getUser().getId())).willReturn(고잉홈());

        given(profileRepository.getByUserId(상품1().getUser().getId())).willReturn(프로필1());
        given(profileRepository.getByUserId(상품2().getUser().getId())).willReturn(프로필2());
        given(profileRepository.getByUserId(상품3().getUser().getId())).willReturn(프로필3());

        ProductViewsAllResponse response = productService.viewAllProduct(pageable);

        assertThat(response.getResponses().get(0).getId()).isEqualTo(전체_조회().getResponses().get(0).getId());
        assertThat(response.getResponses().get(1).getId()).isEqualTo(전체_조회().getResponses().get(1).getId());
        assertThat(response.getResponses().get(2).getId()).isEqualTo(전체_조회().getResponses().get(2).getId());
    }

    @Test
    void viewOneProduct() {
    }

    @Test
    void showSelectedProduct() {
    }

    @Test
    void verifyUser() {
    }

    @Test
    void updateProduct() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void deleteImageByProductId() {
    }
}