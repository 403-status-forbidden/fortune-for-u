package com.a403.ffu.global.security.oauth.mapper;

import com.a403.ffu.global.security.oauth.OAuth2Request;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class KakaoAttributeMapper implements
        AttributeMapper {

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
