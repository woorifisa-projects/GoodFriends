package woorifisa.goodfriends.backend.profile.dto.response;

import lombok.Getter;

import java.util.List;

@Getter
public class ProfileViewsPurchaseList {

    private List<ProfileViewPurchaseList> responses;

    protected ProfileViewsPurchaseList() {
    }

    public ProfileViewsPurchaseList(List<ProfileViewPurchaseList> responses) {
        this.responses = responses;
    }
}
