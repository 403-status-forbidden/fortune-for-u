package com.ssafy.a403.global.config.security.jwt;

import com.ssafy.a403.domain.member.dto.AuthResponse;
import com.ssafy.a403.domain.model.Role;
import com.ssafy.a403.global.config.security.LoginUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class JwtSetupService {

	private final JwtProvider jwtProvider;
    private final JwtValidator jwtValidator;

    @Value("${client.host}")
    private String clientHost;

    @Value("${jwt.refresh-header}")
    private String refreshTokenHeaderTag;

    public HttpHeaders makeAuthorizationHeader(String token) {
        AuthResponse authResponse = createJwtTokenByAuthToken(token);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, authResponse.getAccessToken());
        headers.add(HttpHeaders.SET_COOKIE, authResponse.getRefreshToken());
        log.trace(authResponse.getRefreshToken());
        return headers;
    }

    public List<Role> getRolesFromJwtToken(String token) {

        return jwtValidator.getLoginUser(token).getMember().getRoles();
    }

    public HttpHeaders removeAuthorizationHeader(String refreshToken) {
        String refreshCookie = setCookie(refreshTokenHeaderTag, refreshToken, 0L).toString();
        // 쿠키 삭제
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, null);
        headers.add(HttpHeaders.SET_COOKIE, refreshCookie);
        return headers;
    }
    
    public String createAuthToken(LoginUser loginUser) {
    	return jwtProvider.createAuthToken(loginUser);
    }
    
    public AuthResponse createJwtTokenByAuthToken(String authToken) throws NumberFormatException {
        JwtToken jwtToken = jwtProvider.createJwtTokenByAuthToken(authToken);
        String accessToken = jwtToken.getAccessToken();
        String refreshToken = setCookie(refreshTokenHeaderTag,
                jwtToken.getRefreshToken(),
                JwtProperties.REFRESH_TOKEN_VALIDATION_SECOND).toString();

    	return AuthResponse.of(accessToken, refreshToken);
    }

    private ResponseCookie setCookie(String key, String value, Long maxAge) {
        return ResponseCookie.from(key, value)
                .maxAge(maxAge / 1000)
                .path("/")
                .httpOnly(true)
//                .secure(true)
                .domain(clientHost)
                .build();
    }
}
