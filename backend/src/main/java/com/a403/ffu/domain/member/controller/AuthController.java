package com.ssafy.a403.domain.member.controller;

import com.ssafy.a403.domain.member.dto.AuthRequest;
import com.ssafy.a403.domain.model.Role;
import com.ssafy.a403.global.config.security.jwt.JwtSetupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final JwtSetupService jwtSetupService;

    @PostMapping
    public ResponseEntity<List<Role>> authToken(@RequestBody @Validated AuthRequest authRequest) {
        log.trace("Auth Token: {}", authRequest.getAuthToken());

        HttpHeaders headers = jwtSetupService.makeAuthorizationHeader(authRequest.getAuthToken());
        List<Role> roles = jwtSetupService.getRolesFromJwtToken(authRequest.getAuthToken());

        return ResponseEntity.ok().headers(headers).body(roles);
    }

    @GetMapping("/reissue")
    public ResponseEntity<?> reissue(@CookieValue(name = "Refresh", required = false) Cookie refreshCookie) {
        log.trace("Refresh Token을 이용하여 Access Token 재발급 시도...");
        if (refreshCookie == null) {
            log.trace("Refresh Token이 만료되었습니다.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String refreshToken = refreshCookie.getValue();
        HttpHeaders headers = jwtSetupService.makeAuthorizationHeader(refreshToken);
        List<Role> roles = jwtSetupService.getRolesFromJwtToken(refreshToken);

        return ResponseEntity.ok().headers(headers).body(roles);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(@CookieValue(name = "Refresh", required = false) Cookie refreshCookie) {
        log.trace("로그아웃을 시도합니다.");
        if (refreshCookie == null) {
            log.trace("Refresh Token이 만료되었습니다.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        HttpHeaders headers = jwtSetupService.removeAuthorizationHeader(refreshCookie.getValue());
        return ResponseEntity.ok().headers(headers).build();
    }
}
