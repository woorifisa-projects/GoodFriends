package woorifisa.goodfriends.backend.phoneAuth.presentation;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import woorifisa.goodfriends.backend.phoneAuth.application.SmsService;
import woorifisa.goodfriends.backend.phoneAuth.dto.request.Request;
import woorifisa.goodfriends.backend.phoneAuth.dto.response.SmsResponse;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
@RestController
@RequestMapping("/api/sms")
public class SmsController {

    private SmsService smsService;
    private Request request;

    public SmsController(SmsService smsService) {
        this.smsService = smsService;
    }

    @PostMapping("/user/{phoneNumber}")
    public ResponseEntity<SmsResponse> test(@PathVariable String phoneNumber) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {

        SmsResponse data = smsService.sendSms(phoneNumber);
        return ResponseEntity.ok().body(data);
    }
}
