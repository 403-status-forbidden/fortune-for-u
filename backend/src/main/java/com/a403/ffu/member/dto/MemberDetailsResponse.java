package com.a403.ffu.member.dto;

import com.a403.ffu.member.entity.Member;
import com.a403.ffu.model.Role;
import lombok.Getter;

import java.util.List;

@Getter
public class MemberDetailsResponse extends MemberInfoResponse {

    private final List<FollowerInfoResponse> followInfoList;

    public MemberDetailsResponse(String email, String name, String profileImage, List<Role> roles, List<FollowerInfoResponse> followInfoList) {
        super(email, name, profileImage, roles);
        this.followInfoList = followInfoList;
    }

    public static MemberDetailsResponse of(Member member, List<FollowerInfoResponse> followInfoList) {
        return new MemberDetailsResponse(member.getEmail(), member.getName(), member.getProfileImage(), member.getRoles(), followInfoList);
    }
}
