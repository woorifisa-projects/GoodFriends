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
    private String profileImageUrl;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime lastModifiedAt;
    private int banCount;
    private String phone;
    private String address;
//    private boolean info_activated;


    public UserInfoResponse(String email, String nickname, String profileImageUrl, LocalDateTime createdAt, LocalDateTime lastModifiedAt, int banCount, String phone, String address) {
        this.email = email;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.createdAt = createdAt;
        this.lastModifiedAt = lastModifiedAt;
        this.banCount = banCount;
        this.phone = phone;
        this.address = address;
    }
}
