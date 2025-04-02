package Hello_OAuth2.OAuth2_Study.config;

import Hello_OAuth2.OAuth2_Study.service.CustomOAuth2UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOAuth2UserDetailService customOAuth2UserDetailService;

    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.formLogin((formLogin) -> formLogin.disable());

        http.httpBasic((httpBasic) -> httpBasic.disable());


        // 우리가 구현한 CustomOAuth2UserDetailService 등록
        http.oauth2Login((oauth2Login) -> oauth2Login.userInfoEndpoint(
                (userInfoEndpoint) -> userInfoEndpoint.userService(customOAuth2UserDetailService)));

        http.authorizeHttpRequests((auth) -> auth
                .requestMatchers("/", "/oauth2/**", "/login/**").permitAll()
                .anyRequest().authenticated()
        );


        return http.build();
    }
}
