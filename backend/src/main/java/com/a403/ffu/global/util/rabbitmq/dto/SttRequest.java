package com.ssafy.a403.global.util.rabbitmq.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SttRequest {
    private Long reservationId;
    private String audioFilePath;

    @Builder
    public SttRequest(Long reservationId, String audioFilePath) {
        this.reservationId = reservationId;
        this.audioFilePath = audioFilePath;
    }
}
