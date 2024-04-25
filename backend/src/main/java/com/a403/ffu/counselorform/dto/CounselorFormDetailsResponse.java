package com.a403.ffu.counselorform.dto;

import com.a403.ffu.counselorform.entity.CounselorForm;
import com.a403.ffu.model.CounselorType;
import com.a403.ffu.model.PassState;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CounselorFormDetailsResponse {

    private final String name;

    private final CounselorType counselorType;

    private final String major;

    private final String career;

    private final String intro;

    private final String address;

    private final String phone;

    private final LocalDateTime created;
    private final PassState passState;
    private final String reason;

    public static CounselorFormDetailsResponse of(CounselorForm counselorForm) {

        return new CounselorFormDetailsResponse(
                counselorForm.getMember().getName(),
                counselorForm.getCounselorType(),
                counselorForm.getMajor(),
                counselorForm.getCareer(),
                counselorForm.getIntro(),
                counselorForm.getAddress(),
                counselorForm.getPhone(),
                counselorForm.getCreated(),
                counselorForm.getPassState(),
                counselorForm.getReason());
    }
}
