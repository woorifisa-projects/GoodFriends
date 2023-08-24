package woorifisa.goodfriends.backend.auth.application;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import javax.crypto.SecretKey;

import java.nio.charset.StandardCharsets;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import woorifisa.goodfriends.backend.auth.exception.InvalidTokenException;

// TokenProvider 인터페이스를 구현하는 클래스:  JSON Web Token(JWT)을 생성하는 기능을 담당
@Component
public class JwtTokenProvider implements TokenProvider {

    private final SecretKey key;
    private final long accessTokenValidityInMilliseconds;

    public JwtTokenProvider(@Value("${security.jwt.token.secret-key}") final String secretKey,
                            @Value("${security.jwt.token.access.expire-length}") final long accessTokenValidityInMilliseconds) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        this.accessTokenValidityInMilliseconds = accessTokenValidityInMilliseconds;
    }

    @Override
    public String createAccessToken(String payload) {
        return createToken(payload, accessTokenValidityInMilliseconds);
    }

    public String createToken(final String payload, final Long validityInMilliseconds) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setSubject(payload)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public void validateToken(String accessToken) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(accessToken);

            if (claims.getBody().getExpiration().before(new Date())) { // 만료 시간이 현재 시간보다 이전 시간인지 확인하는 조건문
                throw new InvalidTokenException("토큰이 만료되었습니다.");
            }
        } catch (JwtException | IllegalArgumentException e) {
            throw new InvalidTokenException();
        }
    }

    public String getPayload(final String accessToken) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(accessToken)
                .getBody()
                .getSubject();
    }
}
