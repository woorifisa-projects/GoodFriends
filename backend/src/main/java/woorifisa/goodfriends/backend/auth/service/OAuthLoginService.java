package woorifisa.goodfriends.backend.auth.service;


import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import woorifisa.goodfriends.backend.auth.dto.OAuthConfig;

@Service
public class OAuthLoginService {

    private final Environment env;
    private final RestTemplate restTemplate = new RestTemplate();


    public OAuthLoginService(Environment env) {
        this.env = env;
    }

    //소셜 로그인 메서드
    public void socialLogin(String code, String registrationId) {

        System.out.println("code = " + code);
        System.out.println("registrationId = " + registrationId);

        String accessToken = getAccessToken(code, registrationId);
        System.out.println("accessToken = " + accessToken);

        JsonNode userResourceNode = getUserResource(accessToken,registrationId);
        System.out.println("userResourceNode= " + userResourceNode);

        String id = userResourceNode.get("id").asText();
        String email = userResourceNode.get("email").asText();
        String nickname = userResourceNode.get("name").asText();
        System.out.println(id);
        System.out.println(email);
        System.out.println(nickname);

    }

    //OAuth 값 세팅
    public OAuthConfig getOAuthConfig(String registrationId) {

        OAuthConfig config = new OAuthConfig();

        config.setClientId(env.getProperty("oauth2." + registrationId + ".client-id"));
        config.setClientSecret(env.getProperty("oauth2." + registrationId + ".client-secret"));
        config.setRedirectUri(env.getProperty("oauth2." + registrationId + ".redirect-uri"));
        config.setTokenUri(env.getProperty("oauth2." + registrationId + ".token-uri"));

        return config;
    }

    //액세스 토큰 가져오기
    private String getAccessToken(String authorizationCode, String registrationId) {
        OAuthConfig config = getOAuthConfig(registrationId);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        params.add("code", authorizationCode);
        params.add("client_id", config.getClientId());
        params.add("client_secret", config.getClientSecret());
        params.add("redirect_uri", config.getRedirectUri());
        params.add("grant_type", "authorization_code");


        //HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity entity = new HttpEntity(params, headers);

        //Http POST 방식 요청하기
        ResponseEntity<JsonNode> responseNode = restTemplate.exchange(config.getTokenUri(), HttpMethod.POST, entity, JsonNode.class);
        JsonNode accessTokenNode = responseNode.getBody();

        return accessTokenNode.get("access_token").asText();
    }

    //유저 정보 가져오기
    private JsonNode getUserResource(String accessToken, String registrationId) {
        String resourceUri = env.getProperty("oauth2." + registrationId + ".resource-uri");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer" + accessToken);
        HttpEntity entity = new HttpEntity(headers);

        return restTemplate.exchange(resourceUri, HttpMethod.GET, entity, JsonNode.class).getBody();
    }
}
