package com.ssafy.a403.global.config.security;

import java.util.Collection;
import java.util.Map;

import com.ssafy.a403.domain.member.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Getter;

@Getter
public class LoginUser implements OAuth2User {

	private final Member member;
    private final Map<String, Object> attribute;
    private final Collection<? extends GrantedAuthority> authorities;

    public LoginUser(Member member, Map<String, Object> attribute, Collection<? extends GrantedAuthority> authorities) {
        this.member = member;
        this.attribute = attribute;
        this.authorities = authorities;
    }

    @Override
    public String getName() {
        return member.getNo().toString();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attribute;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
