package com.a403.ffu.model;

import com.a403.ffu.global.security.oauth.mapper.AuthProvider;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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