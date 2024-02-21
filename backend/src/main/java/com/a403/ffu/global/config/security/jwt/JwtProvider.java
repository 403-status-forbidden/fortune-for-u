package com.ssafy.a403.global.config.security.jwt;

import java.security.Key;
import java.util.Date;

import com.ssafy.a403.global.config.security.LoginUser;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtProvider {

	private final Key key;
    
    private final JwtValidator jwtValidator;

    public JwtToken createJwtToken(LoginUser loginUser) {
        Claims claims = getClaims(loginUser);
        log.trace("JWT token created");
        log.trace("loginUser: {}", loginUser.getMember().getName());

        String accessToken = getToken(loginUser, claims, JwtProperties.ACCESS_TOKEN_VALIDATION_SECOND);
        String refreshToken = getToken(loginUser, claims, JwtProperties.REFRESH_TOKEN_VALIDATION_SECOND);

        return new JwtToken(accessToken, refreshToken, JwtProperties.BEARER_TYPE);
    }
    
    public String createAuthToken(LoginUser loginUser) {
        log.trace("Auth token created");
    	Claims claims = getClaims(loginUser);
    	return getToken(loginUser, claims, JwtProperties.AUTH_TOKEN_VALIDATION_SECOND);
    }
    
    public JwtToken createJwtTokenByAuthToken(String authToken) throws NumberFormatException, EntityNotFoundException {
        log.trace("create JWT token by Auth token");
    	return createJwtToken(jwtValidator.getLoginUser(authToken));
    }

    public Claims getClaims(LoginUser loginUser) {
        Claims claims = Jwts.claims();
        claims.put("id", loginUser.getName());
        return claims;
    }

    private String getToken(LoginUser loginUser, Claims claims, Long validationSecond) {
        long now = new Date().getTime();

        return Jwts.builder()
                .setSubject(loginUser.getName())
                .setClaims(claims)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(new Date(now + validationSecond))
                .compact();
    }
}
