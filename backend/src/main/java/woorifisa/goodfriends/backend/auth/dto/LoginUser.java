package woorifisa.goodfriends.backend.auth.dto;

public class LoginUser {

    private Long id;

    public LoginUser() {
    }

    public LoginUser(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
