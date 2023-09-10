package woorifisa.goodfriends.backend.phoneAuth.domain;

public class CreatePhoneAuthNum {

    private final int authNumber= (int) (Math.random() * 100) + 1;;

    public int getAuthNumber() {
        return authNumber;
    }
}
