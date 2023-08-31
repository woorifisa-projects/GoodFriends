package woorifisa.goodfriends.backend.admin.application;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminJwtFilter extends OncePerRequestFilter { // 매번 인증해야하기 때문에

    private final String secretKey;

    public AdminJwtFilter(String secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        // token 안보내면 block
        if(authorization == null || !authorization.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        // token 꺼내기
        String token = authorization.split(" ")[1];

        // token 만료 여부 확인
        if(AdminJwtTokenProvider.isExpired(token, secretKey)) {
            filterChain.doFilter(request, response);
            return;
        }

        // adminId를 token에서 꺼내기
        String adminId = AdminJwtTokenProvider.getAdminId(token, secretKey);

        // 권한 부여
        // token에 있는 adminId를 꺼내서 아래와 같이 넣어주면 controller에서 adminId 사용 가능
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(adminId, null, List.of(new SimpleGrantedAuthority("Admin"))); // DB에 권한(role) 관련 칼럼 있다면 꺼내 올 수도 있음

        // Detail을 넣어줌
        // filterChain에 request 넘겨주면 인증되었다고 기록됨 > 다음 기능으로 넘어갈 수 있음
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
