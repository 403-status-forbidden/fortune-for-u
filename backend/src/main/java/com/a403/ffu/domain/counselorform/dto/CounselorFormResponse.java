package com.ssafy.a403.domain.counselorform.dto;

import com.ssafy.a403.domain.counselorform.entity.CounselorForm;
import com.ssafy.a403.domain.model.PassState;
import lombok.*;

import java.time.LocalDateTime;

@Getter
public class CounselorFormResponse {

    private final Long no;

    private final String name;

    private final LocalDateTime created;

    private final PassState status;

    public CounselorFormResponse(Long no, String name, LocalDateTime created, PassState status) {
        this.no = no;
        this.name = name;
        this.created = created;
        this.status = status;
    }

    public static CounselorFormResponse from(CounselorForm counselorForm) {
        return new CounselorFormResponse(
                counselorForm.getNo(), counselorForm.getMember().getName(),
                counselorForm.getCreated(), counselorForm.getPassState()
        );
    }
}
