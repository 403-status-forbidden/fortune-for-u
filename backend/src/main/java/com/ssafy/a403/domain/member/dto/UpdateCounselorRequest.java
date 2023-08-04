package com.ssafy.a403.domain.member.dto;

import com.ssafy.a403.domain.model.CounselorType;
import lombok.*;

@Data
@Getter
@AllArgsConstructor
public class UpdateCounselorRequest {
    private CounselorType counselorType;
    private String major;
    private String intro;
    private String address;
    private String phone;

    public UpdateCounselorRequest() {

    }
}
