package woorifisa.goodfriends.backend.profile.dto.response;

import lombok.Getter;

import java.util.List;

@Getter
public class ProfilePurchasesResponse {

    private List<ProfilePurchaseResponse> responses;

    protected ProfilePurchasesResponse() {
    }

    public ProfilePurchasesResponse(List<ProfilePurchaseResponse> responses) {
        this.responses = responses;
    }
}
