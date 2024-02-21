package com.ssafy.a403.domain.member.dto;

import com.ssafy.a403.domain.member.entity.Member;
import com.ssafy.a403.domain.model.Role;
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
