package woorifisa.goodfriends.backend.admin.application;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import woorifisa.goodfriends.backend.auth.application.TokenProvider;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.List;

// 관리자의 인증 작업을 처리하는 클래스.
// 인증된 관리자에게만 접근 권한을 부여하고, 해당 사용자의 정보를 SecurityContextHolder에 설정하여 보안 관련 작업을 수행
public class AdminJwtFilter extends OncePerRequestFilter { // 매번 인증해야하기 때문에

    private final TokenProvider tokenProvider;

    public AdminJwtFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    // 메서드는 모든 HTTP 요청마다 단 한 번만 호출되며, 해당 요청을 필터링하고 인증 및 인가 처리 등을 수행할 수 있는 역할을 합니다.
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        // accessToken 안보내면 block
        if(authorization == null || !authorization.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        // accessToken 꺼내기
        String accessToken = authorization.split(" ")[1];

        // accessToken 만료 여부 확인
        tokenProvider.validateToken(accessToken);

        // adminId를 token에서 꺼내기
        String adminId = tokenProvider.getPayload(accessToken);

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
