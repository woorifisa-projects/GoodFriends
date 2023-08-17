package woorifisa.goodfriends.backend.global.config.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtTokenProvider {

    public static String getAdminId(String token, String secretKey) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                .getBody().get("adminId", String.class);
    }

    public static boolean isExpired(String token, String secretKey) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                .getBody().getExpiration().before(new Date());
    }

    public static String createToken(String adminId, String secretKey, Long expireTimeMs) {
        Claims claims = Jwts.claims(); // claims : JWT에서 제공하는 저장 공간으로, 일종의 map
        // claims에 관리자 아이디 정보 넣어주기 (key, value)형태로 저장
        // -> 이렇게 저장해 놓으면 토큰을 받기만 해도 해당 관리자 아이디 사용 가능
        claims.put("adminId", adminId);

        return Jwts.builder()
                .setClaims(claims) // 앞에서 넣은 정보(현재는 관리자 아이디)
                .setIssuedAt(new Date(System.currentTimeMillis())) // 만든 날짜
                .setExpiration(new Date(System.currentTimeMillis() + expireTimeMs)) // 만료 날짜
                .signWith(SignatureAlgorithm.HS256, secretKey) // HS256 알고리즘으로 key를 이용해서 암호화
                .compact();
    }
}

