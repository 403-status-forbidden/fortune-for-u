package com.ssafy.a403.global.config.security.oauth.mapper;

import com.ssafy.a403.global.config.security.oauth.OAuth2Request;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class GoogleAttributeMapper implements AttributeMapper {
    @Override
    public OAuth2Request mapToDto(Map<String, Object> attributes) {
        String accountId = (String) attributes.get("sub");
        String name = (String) attributes.get("name");
        String email = (String) attributes.get("email");
        return new OAuth2Request(accountId, name, email, AuthProvider.GOOGLE);
    }
}
