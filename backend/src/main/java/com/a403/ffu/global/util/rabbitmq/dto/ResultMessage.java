package com.a403.ffu.global.util.rabbitmq.dto;

public record ResultMessage(
        Long reservationId,
        String gptResult) {
}