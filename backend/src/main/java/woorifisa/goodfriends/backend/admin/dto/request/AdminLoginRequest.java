package woorifisa.goodfriends.backend.admin.dto.request;

public class AdminLoginRequest {

    private String adminId;

    private String password;

    public AdminLoginRequest(String adminId, String password) {
        this.adminId = adminId;
        this.password = password;
    }

    public String getAdminId() {
        return adminId;
    }

    public String getPassword() {
        return password;
    }
}
