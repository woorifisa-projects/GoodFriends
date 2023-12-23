package woorifisa.goodfriends.backend.profile.dto.response;

import lombok.Getter;

import java.util.List;

@Getter
public class ProfileSellsResponse {

    private List<ProfileSellResponse> responses;

    protected ProfileSellsResponse() {
    }

    public ProfileSellsResponse(List<ProfileSellResponse> responses) {
        this.responses = responses;
    }
}
