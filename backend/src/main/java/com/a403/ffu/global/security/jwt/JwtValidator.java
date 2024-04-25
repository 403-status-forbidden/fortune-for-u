package com.a403.ffu.global.security.jwt;

import com.a403.ffu.global.security.LoginUser;
import com.a403.ffu.global.security.oauth.mapper.LoginUserMapper;
import jakarta.persistence.EntityNotFoundException;
import java.security.Key;

import com.a403.ffu.member.entity.Member;
import com.a403.ffu.member.service.MemberService;
import io.jsonwebtoken.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtValidator {
	private final Key key;
    private final MemberService memberService;
    private final LoginUserMapper loginUserMapper;

    public Authentication getAuthentication(String token) throws NumberFormatException, EntityNotFoundException {
        Claims claims = getTokenClaims(token);
        Member member = memberService.findById(Long.parseLong(claims.get("id", String.class)));
        LoginUser loginUser = loginUserMapper.toLoginUser(member);

        return new UsernamePasswordAuthenticationToken(loginUser, "", loginUser.getAuthorities());
    }
    
    public LoginUser getLoginUser(String token) throws NumberFormatException, EntityNotFoundException {
    	Claims claims = getTokenClaims(token);
        Member member = memberService.findById(Long.parseLong(claims.get("id", String.class)));
        return loginUserMapper.toLoginUser(member);
    }


    // Token을 파싱하다가 만료된 token이라면 ExpiredJwtException 발생
    private Claims getTokenClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
