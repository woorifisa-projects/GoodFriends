package woorifisa.goodfriends.backend.profile.dto.response;

import lombok.Getter;

import java.util.List;

@Getter
public class ProductViewsSellList {

    private List<ProductViewSellList> responses;

    protected ProductViewsSellList() {
    }

    public ProductViewsSellList(List<ProductViewSellList> responses) {
        this.responses = responses;
    }
}
