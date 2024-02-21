package com.ssafy.a403.domain.reservation.dto;

import com.ssafy.a403.domain.model.ReservationStatus;
import com.ssafy.a403.domain.reservation.entity.CounselingReservation;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
public class ReservationResponse {

    private Long reservationNo;

    private Long memberId;

    private String memberName;

    private Long counselorId;

    private String counselorName;

    private LocalDateTime reservationDateTime;

    private LocalDateTime CancelableReservationDate;

    private ReservationStatus reservationStatus;

    private String sessionId;

    private String reservationReview;

    private String reservationReport;

    private String reservationType;


    public ReservationResponse from(CounselingReservation counselingReservation) {
        ReservationResponse reservationResponse = new ReservationResponse();
        reservationResponse.reservationNo = counselingReservation.getReservationNo();
        reservationResponse.memberId = counselingReservation.getMember().getNo();
        reservationResponse.memberName = counselingReservation.getMember().getName();
        reservationResponse.counselorId = counselingReservation.getCounselor().getNo();
        reservationResponse.counselorName = counselingReservation.getCounselor().getMember().getName();
        reservationResponse.reservationDateTime = counselingReservation.getReservationDateTime();
        reservationResponse.CancelableReservationDate = counselingReservation.getReservationDateTime().minusDays(1);
        reservationResponse.reservationStatus = counselingReservation.getReservationStatus();
        reservationResponse.sessionId = counselingReservation.getSessionId();
        reservationResponse.reservationReview = counselingReservation.getReservationReview();
        reservationResponse.reservationReport = counselingReservation.getReservationReport();
        reservationResponse.reservationType = counselingReservation.getReservationType();

        return reservationResponse;
    }


}
