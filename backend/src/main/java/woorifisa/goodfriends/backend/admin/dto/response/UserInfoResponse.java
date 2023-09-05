package woorifisa.goodfriends.backend.admin.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import woorifisa.goodfriends.backend.profile.domain.Profile;
import woorifisa.goodfriends.backend.user.domain.User;

import java.time.LocalDateTime;

@Getter
public class UserInfoResponse {

    private String email;
    private String nickname;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime created_at;
    private int banCount;
    private String phone;
    private String address;
//    private boolean info_activated;

    public UserInfoResponse(String email, String nickname, LocalDateTime created_at,int banCount, String phone, String address) {
        this.email = email;
        this.nickname = nickname;
        this.created_at = created_at;
        this.banCount = banCount;
        this.phone = phone;
        this.address = address;
//      this.info_activated = info_activated;
    }
}
