package woorifisa.goodfriends.backend.auth.domain;

import org.springframework.stereotype.Component;
import woorifisa.goodfriends.backend.auth.exception.NotFoundTokenException;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryAuthTokenRepository implements TokenRepository {

    private static final Map<Long, String> TOKEN_REPOSITORY = new ConcurrentHashMap<>();

    @Override
    public String save(final Long userId, final String refreshToken) {
        TOKEN_REPOSITORY.put(userId, refreshToken);
        return TOKEN_REPOSITORY.get(userId);
    }

    @Override
    public boolean exist(final Long userId) {
        return TOKEN_REPOSITORY.containsKey(userId);
    }

    @Override
    public String getToken(final Long userId) {
        Optional<String> token = Optional.ofNullable(TOKEN_REPOSITORY.get(userId));
        return token.orElseThrow(() -> new NotFoundTokenException("일치하는 토큰이 존재하지 않습니다."));
    }
}
