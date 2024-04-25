package com.a403.ffu.member.dto;

import com.a403.ffu.model.CounselorType;

public record UpdateCounselorRequest(
        CounselorType counselorType,
        String major,
        String intro,
        String career,
        String address,
        String phone,
        String startTime,
        String endTime) {

}
