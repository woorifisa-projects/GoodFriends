package woorifisa.goodfriends.backend.admin.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import woorifisa.goodfriends.backend.user.domain.User;

import java.time.LocalDateTime;

@Getter
public class UserLogRecordResponse {

    private String email;
    private String nickname;
    private int banCount;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime lastModifiedAt;

    protected UserLogRecordResponse() {
    }

    public UserLogRecordResponse(String email, String nickname, int banCount, LocalDateTime lastModifiedAt) {
        this.email = email;
        this.nickname = nickname;
        this.banCount = banCount;
        this.lastModifiedAt = lastModifiedAt;
    }

    public UserLogRecordResponse(User user) {
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.banCount = user.getBan();
        this.lastModifiedAt = user.getLastModifiedAt();
    }
}
