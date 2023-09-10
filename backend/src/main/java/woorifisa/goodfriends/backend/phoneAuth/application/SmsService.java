package woorifisa.goodfriends.backend.phoneAuth.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import woorifisa.goodfriends.backend.phoneAuth.dto.MessagesDto;
import woorifisa.goodfriends.backend.phoneAuth.dto.request.SmsRequest;
import woorifisa.goodfriends.backend.phoneAuth.dto.response.SmsResponse;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SmsService {


    @Value("${naver-cloud-sms.accessKey}")
    private String accessKey;

    @Value("${naver-cloud-sms.secretKey}")
    private String secretKey;

    @Value("${naver-cloud-sms.serviceId}")
    private String serviceId;

    @Value("${naver-cloud-sms.senderPhone}")
    private String phone;

    public SmsResponse sendSms(String recipientPhoneNumber,int phoneAuthNum) throws JsonProcessingException, UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {

        //현재 시간
        Long time = System.currentTimeMillis();

        //메시지
        List<MessagesDto> messages = new ArrayList<>();
        String subject = "GoodFriends_SMS";
        String content = "[굿프랜즈]인증번호 "+phoneAuthNum+ " 를 입력해주세요.";
        messages.add(new MessagesDto(recipientPhoneNumber, subject, content));

        //시그네처
        CreateSignature createSignature = new CreateSignature();
        String sig = createSignature.getSignature(time,accessKey,secretKey,serviceId); //암호화

        SmsRequest smsRequest = SmsRequest.builder()
                .type("SMS")
                .contentType("COMM")
                .countryCode("82")
                .from(phone)
                .content(content)
                .messages(messages)
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(smsRequest);

        //헤더
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-ncp-apigw-timestamp", time.toString());
        headers.set("x-ncp-iam-access-key", accessKey);
        headers.set("x-ncp-apigw-signature-v2", sig);

        HttpEntity<String> body = new HttpEntity<>(jsonBody,headers);

//        System.out.println(jsonBody);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        SmsResponse smsResponse = null;
        try {
            smsResponse = restTemplate.postForObject(new URI("https://sens.apigw.ntruss.com/sms/v2/services/"+this.serviceId+"/messages"), body, SmsResponse.class);

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return smsResponse;
    }

    public String checkPhoneAuthNum(int inputNumber, int authNumber){
        final String result;

        if(inputNumber == authNumber){
            result = "인증완료";
        }else{
            result = "인증실패";
        }
        return result;
    }
}
