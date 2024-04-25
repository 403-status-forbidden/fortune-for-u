package com.a403.ffu.global.security.oauth;

import com.a403.ffu.global.security.oauth.mapper.AuthProvider;

public record OAuth2Request(String accountId,
                            String name,
                            String email,
                            AuthProvider authProvider) {

}
