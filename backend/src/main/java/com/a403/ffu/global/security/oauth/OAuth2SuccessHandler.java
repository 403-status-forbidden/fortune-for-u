package com.a403.ffu.global.security.oauth;

import com.a403.ffu.global.security.LoginUser;
import com.a403.ffu.global.security.jwt.JwtSetupService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtSetupService jwtSetupService;

    @Value("${client.url}")
    private String clientUrl;

    @Value("${client.endpoint}")
    private String redirectEndPoint;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException {
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // Access Token 발급을 위한 Auth Token을 발급한다
        String authToken = jwtSetupService.createAuthToken(loginUser);
        getRedirectStrategy().sendRedirect(request, response,
                clientUrl + redirectEndPoint + "?token=" + authToken);
    }

}
