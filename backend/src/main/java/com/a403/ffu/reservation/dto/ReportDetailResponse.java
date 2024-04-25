package com.a403.ffu.reservation.dto;

import com.a403.ffu.reservation.entity.CounselingReservation;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ReportDetailResponse {

    private String counselorName;
    private String reservationReport;
    private LocalDateTime reservationDateTime;

    public ReportDetailResponse from(CounselingReservation counselingReservation) {
        ReportDetailResponse reportDetailResponse = new ReportDetailResponse();
        reportDetailResponse.counselorName = counselingReservation.getCounselor().getMember().getName();
        reportDetailResponse.reservationReport = counselingReservation.getReservationReport();
        reportDetailResponse.reservationDateTime = counselingReservation.getReservationDateTime();

        return reportDetailResponse;
    }
}
