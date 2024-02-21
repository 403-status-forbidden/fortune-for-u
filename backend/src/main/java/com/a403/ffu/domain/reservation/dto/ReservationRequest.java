package com.ssafy.a403.domain.reservation.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservationRequest {


    private Long counselorId;
    private String reservationType;
    private LocalDateTime reservationDate;
    private String reservationReview;



    public static ReservationRequest requestData(Long counselorId, String reservationType,LocalDateTime reservationDate, String reservationReview) {

        ReservationRequest reservationRequest = new ReservationRequest();
        reservationRequest.counselorId = counselorId;
        reservationRequest.reservationType = reservationType;
        reservationRequest.reservationDate = reservationDate;
        reservationRequest.reservationReview = reservationReview;

        return reservationRequest;
    }

}