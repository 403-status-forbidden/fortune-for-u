package com.a403.ffu.global.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JwtToken {
	
	private String accessToken;
	private String refreshToken;
	private String grantType;

}
