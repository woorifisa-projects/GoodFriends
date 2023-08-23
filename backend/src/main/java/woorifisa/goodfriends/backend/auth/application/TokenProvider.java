package woorifisa.goodfriends.backend.auth.application;


public interface TokenProvider {
    String createAccessToken(final String payload);
}
