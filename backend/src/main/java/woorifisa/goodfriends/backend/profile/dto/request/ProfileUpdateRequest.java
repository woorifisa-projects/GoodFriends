package woorifisa.goodfriends.backend.profile.dto.request;

import javax.validation.constraints.NotBlank;

public class ProfileUpdateRequest {

    private String email;
    @NotBlank(message = "회원 닉네임이 공백일 수 없습니다.")
    private String nickName;

    @NotBlank(message = "회원 핸드폰 번호는 공백일 수 없습니다.")
    private String mobileNumber;

    @NotBlank(message = "회원 주소는 공백일 수 없습니다.")
    private String address;

    protected ProfileUpdateRequest() {
    }

    public ProfileUpdateRequest(String email, String nickName, String mobileNumber, String address) {
        this.email = email;
        this.nickName = nickName;
        this.mobileNumber = mobileNumber;
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public String getNickName() {
        return nickName;
    }


    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getAddress() {
        return address;
    }
}
