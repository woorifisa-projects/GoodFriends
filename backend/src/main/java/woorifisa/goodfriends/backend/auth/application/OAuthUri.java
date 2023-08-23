package woorifisa.goodfriends.backend.auth.application;

@FunctionalInterface
public interface OAuthUri {
    String generate(final String redirectUri);
}
