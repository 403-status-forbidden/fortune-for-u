package com.ssafy.a403.global.config.security.oauth.mapper;

import com.ssafy.a403.domain.member.entity.Member;
import com.ssafy.a403.global.config.security.LoginUser;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class LoginUserMapper {
	
	public LoginUser toLoginUser(Member member) {
        HashMap<String, Object> attributes = new HashMap<>();
        attributes.put("id", member.getNo());
        return new LoginUser(member, attributes, member.getAuthorityRole());
    }

}
