package com.ssafy.a403.domain.reservation.dto;

import com.ssafy.a403.domain.model.ReportStatus;
import com.ssafy.a403.domain.model.ReservationStatus;
import com.ssafy.a403.domain.reservation.entity.CounselingReservation;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ReportsListResponse {

    private Long memberId;

    private Long reservationNo;

    private Long counselorId;

    private String counselorName;

    private LocalDateTime reservationDateTime;

    private ReportStatus reportStatus;

    private ReservationStatus reservationStatus;

    private String reservationReview;

    private String reservationType;


    public ReportsListResponse from(CounselingReservation counselingReservation) {
        ReportsListResponse reportsListResponse = new ReportsListResponse();
        reportsListResponse.memberId = counselingReservation.getMember().getNo();
        reportsListResponse.reservationNo = counselingReservation.getReservationNo();
        reportsListResponse.counselorId = counselingReservation.getCounselor().getNo();
        reportsListResponse.counselorName = counselingReservation.getCounselor().getMember().getName();
        reportsListResponse.reservationDateTime = counselingReservation.getReservationDateTime();
        reportsListResponse.reportStatus = counselingReservation.getReportStatus();
        reportsListResponse.reservationStatus = counselingReservation.getReservationStatus();
        reportsListResponse.reservationReview = counselingReservation.getReservationReview();
        reportsListResponse.reservationType = counselingReservation.getReservationType();


        return reportsListResponse;
    }

}
