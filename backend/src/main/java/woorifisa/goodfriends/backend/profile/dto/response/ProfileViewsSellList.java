package woorifisa.goodfriends.backend.profile.dto.response;

import lombok.Getter;

import java.util.List;

@Getter
public class ProfileViewsSellList {

    private List<ProfileViewSellList> responses;

    protected ProfileViewsSellList() {
    }

    public ProfileViewsSellList(List<ProfileViewSellList> responses) {
        this.responses = responses;
    }
}
