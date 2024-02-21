package com.ssafy.a403.global.config.security.oauth.mapper;

import com.ssafy.a403.global.config.security.oauth.OAuth2Request;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class KakaoAttributeMapper implements AttributeMapper {
    @Override
    public OAuth2Request mapToDto(Map<String, Object> attributes) {
        Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");
        Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");
        String accountId = Long.toString((Long) attributes.get("id"));
        String name = (String) properties.get("nickname");
        String email = (String) account.get("email");
        return new OAuth2Request(accountId, name, email, AuthProvider.KAKAO);
    }
}
