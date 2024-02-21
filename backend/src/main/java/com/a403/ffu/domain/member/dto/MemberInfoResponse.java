package com.ssafy.a403.domain.member.dto;

import com.ssafy.a403.domain.member.entity.Member;
import com.ssafy.a403.domain.model.Role;
import lombok.Getter;

import java.util.List;

@Getter
public class MemberInfoResponse {

    private final String email;
    private final String name;
    private final String profileImage;
    private final List<Role> role;

    public MemberInfoResponse(String email, String name, String profileImage, List<Role> role) {
        this.email = email;
        this.name = name;
        this.profileImage = profileImage;
        this.role = role;
    }

    public static MemberInfoResponse of(Member member) {
        return new MemberInfoResponse(member.getEmail(), member.getName(), member.getProfileImage(), member.getRoles());
    }
}
