package com.a403.ffu.member.dto;

import com.a403.ffu.member.entity.Counselor;
import com.a403.ffu.model.CounselorType;
import lombok.Builder;

@Builder
public record CounselorInfoResponse(
        String name,
        Long memberNo,
        String profileImg,
        Long counselorNo,
        CounselorType counselorType,
        String major,
        String intro,
        String career,
        String address,
        String phone,
        Integer reviewCnt,
        Float ratingAvg,
        String startTime,
        String endTime) {

    public static CounselorInfoResponse from(Counselor counselor) {

        return CounselorInfoResponse.builder()
                .name(counselor.getMember().getName())
                .memberNo(counselor.getMember().getNo())
                .profileImg(counselor.getMember().getProfileImage())
                .counselorNo(counselor.getNo())
                .counselorType(counselor.getCounselorType())
                .major(counselor.getMajor())
                .intro(counselor.getIntro())
                .career(counselor.getCareer())
                .address(counselor.getAddress())
                .phone(counselor.getPhone())
                .reviewCnt(counselor.getReviewCnt())
                .ratingAvg(counselor.getRatingAvg())
                .startTime(counselor.getStartTime())
                .endTime(counselor.getEndTime())
                .build();
    }
}
