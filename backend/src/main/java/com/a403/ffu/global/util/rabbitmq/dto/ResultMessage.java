package com.ssafy.a403.global.util.rabbitmq.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResultMessage {
    private Long reservationId;
    private String gptResult;

    @Builder
    public ResultMessage(Long reservationId, String gptResult) {
        this.reservationId = reservationId;
        this.gptResult = gptResult;
    }
}