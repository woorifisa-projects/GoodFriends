package woorifisa.goodfriends.backend.admin.dto.request;

public class AdminLoginRequest {

    private String root;

    private String password;

    public AdminLoginRequest() {
    }

    public AdminLoginRequest(String root, String password) {
        this.root = root;
        this.password = password;
    }

    public String getRoot() {
        return root;
    }

    public String getPassword() {
        return password;
    }
}
