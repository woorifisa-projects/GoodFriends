package woorifisa.goodfriends.backend.infrastructure.oauth.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import woorifisa.goodfriends.backend.auth.application.OAuthClient;
import woorifisa.goodfriends.backend.auth.dto.OAuthUser;
import woorifisa.goodfriends.backend.global.config.properties.GoogleProperties;
import woorifisa.goodfriends.backend.infrastructure.dto.GoogleTokenResponse;
import woorifisa.goodfriends.backend.infrastructure.oauth.dto.UserInfo;
import woorifisa.goodfriends.backend.infrastructure.oauth.exception.OAuthException;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

// 구글 OAuth 서비스로부터 받은 인가 코드를 사용하여 액세스 토큰을 받아오고, 해당 토큰의 페이로드를 디코딩하여 사용자 정보를 추출하는 역할을 수행
@Component
public class GoogleOAuthClient implements OAuthClient {

    private static final String JWT_DELIMITER = "\\.";

    private final GoogleProperties properties;

    private final RestTemplate restTemplate;

    private final ObjectMapper objectMapper;

    public GoogleOAuthClient(final GoogleProperties properties,
                             final RestTemplateBuilder restTemplateBuilder,
                             final ObjectMapper objectMapper) {
        this.properties = properties;
        this.restTemplate = restTemplateBuilder.build();
        this.objectMapper = objectMapper;
    }

    // 인가 코드와 리다이렉트 URI를 인자로 받아 사용자 정보를 가져오는 작업을 수행합니다.
    @Override
    public OAuthUser getOAuthUser(final String code, final String redirectUri) {
        GoogleTokenResponse googleTokenResponse = requestGoogleToken(code, redirectUri); // 1
        String payload = getPayload(googleTokenResponse.getIdToken()); // 2
        UserInfo userInfo = parseUserInfo(payload);

        return new OAuthUser(userInfo.getEmail(), userInfo.getName(), userInfo.getPicture()); // OAuthMember 객체에 사용자의 이메일, 이름 및 프로필 사진 정보를 담아 반환합니다.
    }

    // 1-1
    // 구글에게 인가 코드를 보내어 액세스 토큰을 받아오는 메서드입니다.
    // generateTokenParams 메서드를 사용하여 필요한 파라미터들을 설정합니다.
    // restTemplate을 사용하여 POST 요청을 보내고 액세스 토큰을 받아옵니다.
    private GoogleTokenResponse requestGoogleToken(final String code, final String redirectUri) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> params = generateTokenParams(code, redirectUri);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        return fetchGoogleToken(request).getBody();
    }

    // 1-2
    // 구글 OAuth 서비스로 액세스 토큰을 요청하기 위해 필요한 파라미터들을 생성하고 그것들을 MultiValueMap 객체에 담아 반환하는 역할을 합니다.
    // 각각의 파라미터는 OAuth 2.0 표준에 따라서 정의된 것들입니다.
    private MultiValueMap<String, String> generateTokenParams(String code, String redirectUri) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client_id", properties.getClientId());
        params.add("client_secret", properties.getClientSecret());
        params.add("code", code);
        params.add("grant_type", "authorization_code");
        params.add("redirect_uri", redirectUri);
        return params;
    }

    // 1-3
    // 실제로 구글 서버에 액세스 토큰을 요청하는 역할을 합니다.
    //  POST 요청을 사용하여 구글 OAuth 서버에 액세스 토큰을 요청하고, 응답을 GoogleTokenResponse 객체로 변환하여 반환합니다.
    private ResponseEntity<GoogleTokenResponse> fetchGoogleToken(
            final HttpEntity<MultiValueMap<String, String>> request) {
        try {
            return restTemplate.postForEntity(properties.getTokenUri(), request, GoogleTokenResponse.class);
        } catch (final RestClientException e) {
            throw new OAuthException(e);
        }
    }

    // 2-1
    // JWT 형식의 토큰에서 페이로드 부분을 추출합니다.
    private String getPayload(final String jwt) {
        return jwt.split(JWT_DELIMITER)[1];
    }

    // 2-2
    // JWT의 페이로드를 디코딩하여 JSON 형식의 사용자 정보를 추출합니다.
    // 이때 objectMapper를 사용하여 JSON을 파싱하여 UserInfo 객체로 변환합니다.
    private UserInfo parseUserInfo(final String payload) {
        String decodedPayload = decodeJwtPayload(payload);
        try {
            return objectMapper.readValue(decodedPayload, UserInfo.class);
        } catch (final JsonProcessingException e) {
            throw new OAuthException("id 토큰을 읽을 수 없습니다.", e);
        }
    }

    // 2-3
    // JWT 토큰의 페이로드를 디코딩하는 메서드입니다.
    private String decodeJwtPayload(final String payload) {
        return new String(Base64.getUrlDecoder().decode(payload), StandardCharsets.UTF_8);
    }
}
