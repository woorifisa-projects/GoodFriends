package woorifisa.goodfriends.backend.admin.dto.response;

import java.time.LocalDateTime;

public class LogViewResponse {

    private String email;
    private String nickname;
    private int banCount;
    private LocalDateTime lastModifiedAt;

    public LogViewResponse() {
    }

    public LogViewResponse(String email, String nickname, int banCount, LocalDateTime lastModifiedAt) {
        this.email = email;
        this.nickname = nickname;
        this.banCount = banCount;
        this.lastModifiedAt = lastModifiedAt;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public int getBanCount() {
        return banCount;
    }

    public LocalDateTime getLastModifiedAt() {
        return lastModifiedAt;
    }
}
