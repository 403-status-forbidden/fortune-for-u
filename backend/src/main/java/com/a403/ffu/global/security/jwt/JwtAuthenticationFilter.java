package com.a403.ffu.global.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * dispatcher servlet 이전에 실행되는 filter 헤더에서 access token 추출 Authentication 검증 SecurityContextHolder에
 * Authentication 등록
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final ApplicationContext applicationContext;
    private final JwtValidator jwtValidator;

    // Token Validation에서 제외할 경로들
    private static final List<String> EXCLUDE_URL = List.of(
            "/favicon.ico",
            "/api/auth", "/api/auth/reissue",  // 인증 관련(토큰 발급, 재발급)
            "/api/counselors", "/api/counselors/{counselorNo}/", "/api/counselors/by_ratings",
            "/api/counselors/by_reviews",
            "/api/reservations/availabledate/**", "/api/reservations/{counselorId}/co_reviews"
    );

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return EXCLUDE_URL.stream()
                .anyMatch(exclude -> exclude.equalsIgnoreCase(request.getServletPath()));
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (applicationContext != null) {
            String[] beans = applicationContext.getBeanDefinitionNames();

            for (String bean : beans) {
                log.error("bean: {}", bean);
            }
        }
        Optional<String> token = Optional.ofNullable(getTokensFromHeader(request));
        log.debug("Token: {}", token);
        log.debug("Request URI: {}", request.getRequestURI());
        token.ifPresent(
                t -> {
                    log.info("AccessToken: {}", t);
                    // 만약 여기서 예외가 발생한다면 - access 토큰이 invalid
                    // ExceptionHandlerFilter에서 예외를 처리한다. (에러코드 반환)
                    Authentication authentication = jwtValidator.getAuthentication(t);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                });
        filterChain.doFilter(request, response);
    }

    private String getTokensFromHeader(HttpServletRequest request) {
        return request.getHeader(HttpHeaders.AUTHORIZATION);
    }
}
