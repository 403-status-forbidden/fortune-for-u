package com.ssafy.a403.global.config.security.oauth.mapper;

import com.ssafy.a403.global.config.security.oauth.OAuth2Request;

import java.util.Map;

public interface AttributeMapper {
	OAuth2Request mapToDto(Map<String, Object> attributes);

}
