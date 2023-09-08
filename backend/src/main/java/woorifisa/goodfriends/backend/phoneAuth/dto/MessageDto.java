package woorifisa.goodfriends.backend.phoneAuth.dto;


import lombok.Builder;

@Builder
public class MessageDto {
    private String to;

    public MessageDto(String to) {
        this.to = to;
    }

    public String getTo() {
        return to;
    }
}
