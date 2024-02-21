package com.ssafy.a403.domain.reservation.dto;

import com.ssafy.a403.domain.reservation.entity.CounselingReservation;
import lombok.Getter;


@Getter
public class ReportResponse {

    private Long reservationNo;

    private String reservationReport;

    public ReportResponse(Long reservationNo, String reservationReport) {
        this.reservationNo = reservationNo;
        this.reservationReport = reservationReport;
    }

    public static ReportResponse of(CounselingReservation counselingReservation) {
        return new ReportResponse(counselingReservation.getReservationNo(), counselingReservation.getReservationReport());
    }
}