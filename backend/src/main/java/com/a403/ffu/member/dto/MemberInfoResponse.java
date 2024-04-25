package com.a403.ffu.member.dto;

import com.a403.ffu.member.entity.Member;
import com.a403.ffu.model.Role;
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
