package com.a403.ffu.global.security.oauth.mapper;

import com.a403.ffu.global.security.oauth.OAuth2Request;
import java.util.Map;

public interface AttributeMapper {

    OAuth2Request mapToDto(Map<String, Object> attributes);

}
