package com.a403.ffu.global.security.oauth.mapper;

import com.a403.ffu.global.security.LoginUser;
import com.a403.ffu.member.entity.Member;
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
