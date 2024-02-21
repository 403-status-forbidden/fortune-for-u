package com.ssafy.a403.domain.counselorform.dto;

import com.ssafy.a403.domain.model.PassState;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CounselorFormUpdateRequest {

    private final String reason;
    private final PassState passState;
}
