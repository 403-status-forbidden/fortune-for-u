package com.a403.ffu.global.security.jwt;

public interface JwtProperties {

    Long AUTH_TOKEN_VALIDATION_SECOND = 60L * 1000;
    Long ACCESS_TOKEN_VALIDATION_SECOND = 30 * 60L * 1000;
    Long REFRESH_TOKEN_VALIDATION_SECOND = 14 * 24 * 60 * 60L * 1000;
    String BEARER_TYPE = "bearer";
}
