package com.a403.ffu.reservation.dto;

public record UpdateScheduleRequest(
        String mon,
        String tue,
        String wed,
        String thu,
        String fri,
        String sat,
        String sun) {
}
