package woorifisa.goodfriends.backend.common.fixtures;

import woorifisa.goodfriends.backend.user.domain.User;
import woorifisa.goodfriends.backend.user.dto.response.UserResponse;

public class UserFixtures {

    /* 관리자 */
    public static final String 관리자_이메일 = "goodfriends.admin@gmail.com";
    public static final String 관리자_닉네임 = "관리자";
    public static final String 관리자_프로필 = "/admin.png";
    public static final UserResponse 관리자_응답 = new UserResponse(1L, 관리자_이메일, 관리자_닉네임, 관리자_프로필);

    /* 팬시 */
    public static final String 팬시_이메일 = "fancy@gmail.com";
    public static final String 팬시_닉네임 = "팬시";
    public static final String 팬시_프로필 = "/fancy.png";
    public static final UserResponse 팬시_응답 = new UserResponse(1L, 팬시_이메일, 팬시_닉네임, 팬시_프로필);

    /* 코코 */
    public static final String 코코_이메일 = "coco@gmail.com";
    public static final String 코코_닉네임 = "코코";
    public static final String 코코_프로필 = "/coco.png";
    public static final UserResponse 코코_응답 = new UserResponse(1L, 코코_이메일, 코코_닉네임, 코코_프로필);

    /* 춘식 */
    public static final String 춘식_이메일 = "chunsick@gmail.com";
    public static final String 춘식_닉네임 = "춘식";
    public static final String 춘식_프로필 = "/chunsick.png";
    public static final UserResponse 춘식_응답 = new UserResponse(1L, 춘식_이메일, 춘식_닉네임, 춘식_프로필);

    /* 고잉홈 */
    public static final String 고잉홈_이메일 = "goinghome@gmail.com";
    public static final String 고잉홈_닉네임 = "고잉홈";
    public static final String 고잉홈_프로필 = "/goinghome.png";
    public static final UserResponse 고잉홈_응답 = new UserResponse(1L, 고잉홈_이메일, 고잉홈_닉네임, 고잉홈_프로필);

    public static User 관리자() {
        return new User(관리자_이메일, 관리자_닉네임 ,관리자_프로필);
    }

    public static User 팬시() {
        return new User(팬시_이메일, 팬시_닉네임, 팬시_프로필);
    }

    public static User 코코() {
        return new User(코코_이메일, 코코_닉네임, 코코_프로필);
    }

    public static User 춘식() {
        return new User(춘식_이메일, 춘식_닉네임, 춘식_프로필);
    }

    public static User 고잉홈() {
        return new User(고잉홈_이메일, 고잉홈_닉네임, 고잉홈_프로필);
    }
}
