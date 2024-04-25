package com.a403.ffu.reservation.dto;


public record ReviewRequest(
        Long counselorId,
        String reservationReview,
        Float reservationScore) {

    public static ReviewRequest of(
            Long counselorId,
            String reservationReview,
            Float reservationScore) {

        return new ReviewRequest(counselorId, reservationReview, reservationScore);
    }
}
