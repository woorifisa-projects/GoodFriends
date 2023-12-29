package woorifisa.goodfriends.backend.auth.application;


public interface TokenProvider {
    String createAccessToken(final String payload);

    void validateToken(final String accessToken);

    String getPayload(final String accessToken);

    String createRefreshToken(final String payload);

}
