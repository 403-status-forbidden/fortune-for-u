package com.a403.ffu.config;

import com.a403.ffu.global.security.exception.ExceptionHandlerFilter;
import com.a403.ffu.global.security.jwt.JwtAuthenticationFilter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.oauth2.client.OAuth2LoginConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationSuccessHandler successHandler;
    private final AuthenticationFailureHandler failureHandler;
    private final AccessDeniedHandler accessDeniedHandler;
    // <Member, Long> 이런 느낌이라고 생각하면 됨
    private final OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final ExceptionHandlerFilter exceptionHandlerFilter;

    @Value("${client.url}")
    private String clientUrl;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web ->
                web.ignoring()
                        .requestMatchers(PathRequest.toH2Console())
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .exceptionHandling(
                        exception ->
                                exception.authenticationEntryPoint(new Http403ForbiddenEntryPoint())
                                        .accessDeniedHandler(accessDeniedHandler))
                .authorizeHttpRequests(
                        authorize ->
                                authorize
                                        .requestMatchers(
                                                new AntPathRequestMatcher("/api/auth/**"),
                                                new AntPathRequestMatcher("/api/counselors/**"),
                                                new AntPathRequestMatcher("/api/counselors/**"),
                                                new AntPathRequestMatcher("/api/reservations/**"),
                                                new AntPathRequestMatcher(
                                                        "/api/recording/{sessionId}"))
                                        .permitAll()
//                                        .requestMatchers(
//                                                "/api/roomsession", "/api/sessions/{sessionId}",
//                                                "/api/reservations/counselor_rez_info/{date}",
//                                                "/api/reservations/counseling_results/{reservationNo}",
//                                                "/api/reservations/co_reviews",
//                                                "/api/reservations/counseling_results/{reservationNo}",
//                                                "/api/counselors/time/update/{counselorNo}",
//                                                "/api/counselors/info", "/api/counselors/update"
//                                        ).hasAnyRole("COUNSELOR")
//                                        .requestMatchers(
//                                                "/api/admin/counselor-forms",
//                                                "/api/admin/counselor-forms/**",
//                                                "/api/admin/counselor-forms/{counselorFormNo}/update"
//                                        ).hasAnyRole("ADMIN")
                                        .anyRequest().authenticated())
                .oauth2Login(setOAuth2Config())
                .sessionManagement(
                        config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter,
                        OAuth2LoginAuthenticationFilter.class)
                .addFilterBefore(exceptionHandlerFilter, JwtAuthenticationFilter.class)
                .build();
    }

    private Customizer<OAuth2LoginConfigurer<HttpSecurity>> setOAuth2Config() {

        return o ->
                o.authorizationEndpoint(auth -> auth.baseUri(
                                "/oauth2/authorize"))  // defalut는 /oauth2/authorization
                        .userInfoEndpoint(e -> e.userService(
                                oAuth2UserService))    // 공급자가 제공한 사용자 정보를 로드한다 -> LoginUser와 매핑
                        // .tokenEndpoint() // 이처럼 tokenEndpoint()를 명시해주지 않으면 default는 spring에 기본 구현한대로 동작 -> code와 token을 교환
                        // .accessTokenResponseClient(accessTokenResponseClient());
                        // .redirectionEndpoint()를 명시해주지 않으면 default는 login/oauth2/code
                        .successHandler(successHandler)
                        .failureHandler(failureHandler);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of(clientUrl));
        configuration.setAllowedHeaders(
                List.of(HttpHeaders.AUTHORIZATION, HttpHeaders.CONTENT_TYPE));
        configuration.setExposedHeaders(List.of(HttpHeaders.AUTHORIZATION, HttpHeaders.SET_COOKIE));
        configuration.setAllowedMethods(
                List.of(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(),
                        HttpMethod.PATCH.name(), HttpMethod.DELETE.name()));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}


