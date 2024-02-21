package com.ssafy.a403.domain.member.dto;

import com.ssafy.a403.domain.member.entity.Counselor;
import com.ssafy.a403.domain.member.entity.Member;
import com.ssafy.a403.domain.model.CounselorType;
import com.ssafy.a403.domain.model.Role;
import lombok.Getter;

import java.util.List;

@Getter
public class FollowerInfoResponse {

    private final Long memberNo;
    private final String email;
    private final String name;
    private final String profileImage;
    private final List<Role> role;
    private final Long counselorNo;
    private final CounselorType counselorType;
    private final String major;
    private final String intro;
    private final String career;
    private final String address;
    private final String phone;
    private final int reviewCnt;
    private final float ratingAvg;

    public FollowerInfoResponse(Long memberNo, String email, String name, String profileImage, List<Role> role,
                                Long counselorNo, int reviewCnt, float ratingAvg, String major, String intro,
                                String career, String address, String phone, CounselorType counselorType) {
        this.memberNo = memberNo;
        this.email = email;
        this.name = name;
        this.profileImage = profileImage;
        this.role = role;
        this.counselorNo = counselorNo;
        this.reviewCnt = reviewCnt;
        this.ratingAvg = ratingAvg;
        this.major = major;
        this.intro = intro;
        this.career = career;
        this.address = address;
        this.phone = phone;
        this.counselorType = counselorType;
    }

    public static FollowerInfoResponse of(Member member) {
        Counselor counselor = member.getCounselor();
        return new FollowerInfoResponse(member.getNo(), member.getEmail(), member.getName(), member.getProfileImage(), member.getRoles(),
                counselor.getNo(), counselor.getReviewCnt(), counselor.getRatingAvg(), counselor.getMajor(),
                counselor.getIntro(), counselor.getCareer(), counselor.getAddress(), counselor.getPhone(), counselor.getCounselorType());
    }
}
