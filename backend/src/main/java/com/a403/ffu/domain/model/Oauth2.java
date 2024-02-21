package com.ssafy.a403.domain.model;

import com.ssafy.a403.global.config.security.oauth.mapper.AuthProvider;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Oauth2 {
    @Enumerated(EnumType.STRING)
    private AuthProvider authProvider;

    @Column(unique = true)
    private String accountId;

    public Oauth2(AuthProvider authProvider, String accountId) {
        this.authProvider = authProvider;
        this.accountId = accountId;
    }
}