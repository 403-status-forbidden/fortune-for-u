package com.a403.ffu.member.controller;

import com.a403.ffu.global.security.jwt.JwtSetupService;
import com.a403.ffu.member.dto.AuthRequest;
import com.a403.ffu.model.Role;
import jakarta.servlet.http.Cookie;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final JwtSetupService jwtSetupService;

    @PostMapping
    public ResponseEntity<List<Role>> authToken(@RequestBody @Validated AuthRequest authRequest) {
        log.debug("Auth Token: {}", authRequest.authToken());

        HttpHeaders headers = jwtSetupService.makeAuthorizationHeader(authRequest.authToken());
        List<Role> roles = jwtSetupService.getRolesFromJwtToken(authRequest.authToken());

        return ResponseEntity.ok().headers(headers).body(roles);
    }

    @GetMapping("/reissue")
    public ResponseEntity<?> reissue(@CookieValue(name = "Refresh", required = false) Cookie refreshCookie) {
        log.debug("Refresh Token을 이용하여 Access Token 재발급 시도...");
        if (refreshCookie == null) {
            log.debug("Refresh Token이 만료되었습니다.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String refreshToken = refreshCookie.getValue();
        HttpHeaders headers = jwtSetupService.makeAuthorizationHeader(refreshToken);
        List<Role> roles = jwtSetupService.getRolesFromJwtToken(refreshToken);

        return ResponseEntity.ok().headers(headers).body(roles);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(@CookieValue(name = "Refresh", required = false) Cookie refreshCookie) {
        log.debug("로그아웃을 시도합니다.");
        if (refreshCookie == null) {
            log.debug("Refresh Token이 만료되었습니다.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        HttpHeaders headers = jwtSetupService.removeAuthorizationHeader(refreshCookie.getValue());
        return ResponseEntity.ok().headers(headers).build();
    }
}
