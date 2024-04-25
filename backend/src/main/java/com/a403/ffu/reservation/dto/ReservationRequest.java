package com.a403.ffu.reservation.dto;

import com.a403.ffu.reservation.entity.CounselingReservation;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record ReservationRequest(
        Long counselorId,
        String reservationType,
        LocalDateTime reservationDate,
        String reservationReview) {

    public static ReservationRequest of(CounselingReservation reservation) {

        return ReservationRequest.builder()
                .counselorId(reservation.getCounselor().getNo())
                .reservationType(reservation.getReservationType())
                .reservationDate(reservation.getReservationDateTime())
                .reservationReview(reservation.getReservationReview())
                .build();
    }

}