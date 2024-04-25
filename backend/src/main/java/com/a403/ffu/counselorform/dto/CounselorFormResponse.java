package com.a403.ffu.counselorform.dto;

import com.a403.ffu.counselorform.entity.CounselorForm;
import com.a403.ffu.model.PassState;
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
