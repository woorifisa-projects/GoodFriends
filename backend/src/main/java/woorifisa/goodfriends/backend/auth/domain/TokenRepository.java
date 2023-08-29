package woorifisa.goodfriends.backend.auth.domain;


public interface TokenRepository{
    boolean exist(final Long userId);
    String getToken(final Long userId);

    String save(final Long userId, final String refreshToken);
}
