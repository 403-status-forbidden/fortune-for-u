package com.a403.ffu.global.util.rabbitmq.dto;

public record SttRequest(
        Long reservationId,
        String audioFilePath) {

}
