package woorifisa.goodfriends.backend.phoneAuth.presentation;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import woorifisa.goodfriends.backend.phoneAuth.application.CreatePhoneAuthNum;
import woorifisa.goodfriends.backend.phoneAuth.application.SmsService;
import woorifisa.goodfriends.backend.phoneAuth.dto.request.Request;
import woorifisa.goodfriends.backend.phoneAuth.dto.response.SmsResponse;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
@RestController
@RequestMapping("/api/sms")
public class SmsController {

    private final SmsService smsService;
    private Request request;
    private CreatePhoneAuthNum createPhoneAuthNum;
    public SmsController(SmsService smsService) {
        this.smsService = smsService;
    }

    //SMS 인증번호 전송
    @PostMapping("/user")
    public ResponseEntity<SmsResponse> phoneNumberAuth(@RequestBody Request request) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {
        this.createPhoneAuthNum = new CreatePhoneAuthNum();
        SmsResponse data = smsService.sendSms(request.getRecipientPhoneNumber(),createPhoneAuthNum.getAuthNumber());

        return ResponseEntity.ok().body(data);
    }

    //SMS 인증번호 검증
    @GetMapping("/user/check/{inputAuthNum}")
    public String receiveAuthNumber (@PathVariable int inputAuthNum){
        String result = smsService.checkPhoneAuthNum(inputAuthNum,createPhoneAuthNum.getAuthNumber());

        return result;
    }
}
