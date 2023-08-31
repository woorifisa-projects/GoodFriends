package woorifisa.goodfriends.backend.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import woorifisa.goodfriends.backend.admin.application.AdminAuthenticationFilter;
import woorifisa.goodfriends.backend.auth.application.TokenProvider;

@Configuration
@EnableWebSecurity
public class SecurityAuthenticationConfig {

    private final TokenProvider tokenProvider;

    public SecurityAuthenticationConfig(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    // SpringSecurity 적용하면 모든 api에 인증이 필요하다고 default로 호출됨
    // 로그인이나 회원가입같이 인증받지 않고 접속해야하는 부분 설정할 수 있는 부분
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .httpBasic().disable() // 토큰 인증할 것이므로 disable
                .csrf().disable()
                .cors().and()
                .authorizeRequests()
                .antMatchers("api/admin/login").permitAll() // 로그인은 언제나 가능
                .antMatchers(HttpMethod.POST, "api/admin/**").authenticated()
                .antMatchers(HttpMethod.GET, "api/admin/**").authenticated()
                .antMatchers(HttpMethod.PUT, "api/admin/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "api/admin/**").authenticated()
                .and()
                .addFilterBefore(new AdminAuthenticationFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class)
                .build();

    }
}