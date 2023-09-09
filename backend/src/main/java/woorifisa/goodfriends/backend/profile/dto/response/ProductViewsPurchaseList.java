package woorifisa.goodfriends.backend.profile.dto.response;

import lombok.Getter;

import java.util.List;

@Getter
public class ProductViewsPurchaseList {

    private List<ProductViewPurchaseList> responses;

    public ProductViewsPurchaseList() {
    }

    public ProductViewsPurchaseList(List<ProductViewPurchaseList> responses) {
        this.responses = responses;
    }
}
