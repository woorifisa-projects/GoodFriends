package woorifisa.goodfriends.backend.global.config.webconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import woorifisa.goodfriends.backend.admin.application.AdminService;
import woorifisa.goodfriends.backend.global.config.utils.JwtFilter;

@Configuration
@EnableWebSecurity
public class AuthenticationConfig {

    @Value("${oauth2.jwt.secret}")
    private String secretKey;

    // SpringSecurity 적용하면 모든 api에 인증이 필요하다고 default로 호출됨
    // 로그인이나 회원가입같이 인증받지 않고 접속해야하는 부분 설정할 수 있는 부분
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .httpBasic().disable() // 토큰 인증할 것이므로 disable
                .csrf().disable()
                .cors().and()
                .authorizeRequests()
                .antMatchers("/admin/login").permitAll() // 로그인은 언제나 가능
                .antMatchers(HttpMethod.POST, "/admin/**").authenticated()
                .and()
                .addFilterBefore(new JwtFilter(secretKey), UsernamePasswordAuthenticationFilter.class)
                .build();

    }
}