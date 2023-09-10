package woorifisa.goodfriends.backend.phoneAuth.application;

public class CreatePhoneAuthNum {

    private final int authNumber= (int) (Math.random() * 100) + 1;;

    public int getAuthNumber() {
        return authNumber;
    }
}
