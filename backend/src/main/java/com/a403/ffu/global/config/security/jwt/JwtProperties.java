package com.ssafy.a403.global.config.security.jwt;

import org.springframework.beans.factory.annotation.Value;

public interface JwtProperties {

    Long AUTH_TOKEN_VALIDATION_SECOND = 60L * 1000;
    Long ACCESS_TOKEN_VALIDATION_SECOND = 30 * 60L * 1000;
    Long REFRESH_TOKEN_VALIDATION_SECOND = 14 * 24 * 60 * 60L * 1000;
    String BEARER_TYPE = "bearer";
}
