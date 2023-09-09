package woorifisa.goodfriends.backend.product.dto.response;

import lombok.Getter;

import java.util.List;

@Getter
public class ProductViewsSellList {

    private List<ProductViewSellList> responses;

    public ProductViewsSellList() {
    }

    public ProductViewsSellList(List<ProductViewSellList> responses) {
        this.responses = responses;
    }
}
