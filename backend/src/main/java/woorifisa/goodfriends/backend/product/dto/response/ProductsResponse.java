package woorifisa.goodfriends.backend.product.dto.response;

import lombok.Getter;

import java.util.List;

@Getter
public class ProductsResponse {

    private List<ProductResponse> responses;

    public ProductsResponse() {
    }

    public ProductsResponse(List<ProductResponse> responses) {
        this.responses = responses;
    }
}
