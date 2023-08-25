package woorifisa.goodfriends.backend.auth.application;


public interface TokenProvider {
    String createAccessToken(final String payload);

    void validateToken(String accessToken);

    String getPayload(String accessToken);
}
