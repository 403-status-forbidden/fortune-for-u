package com.a403.ffu.counselorform.dto;

import com.a403.ffu.model.PassState;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CounselorFormUpdateRequest {

    private final String reason;
    private final PassState passState;
}
