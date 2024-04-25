package com.a403.ffu.reservation.dto;

import com.a403.ffu.reservation.entity.CounselingReservation;

public record ReportResponse(
        Long reservationNo, String reservationReport) {

    public static ReportResponse of(CounselingReservation counselingReservation) {
        return new ReportResponse(counselingReservation.getReservationNo(), counselingReservation.getReservationReport());
    }
}