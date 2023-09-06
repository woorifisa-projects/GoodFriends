package woorifisa.goodfriends.backend.product.dto.response;

import lombok.Getter;
import woorifisa.goodfriends.backend.product.domain.Product;
import woorifisa.goodfriends.backend.profile.domain.Profile;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ProductViewsAllResponse {

    List<ProductViewAllResponse> responses;

    public ProductViewsAllResponse() {
    }

    public ProductViewsAllResponse(List<ProductViewAllResponse> responses) {
        this.responses = responses;
    }
}
