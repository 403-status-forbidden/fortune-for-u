package com.ssafy.a403.domain.reservation.dto;


import lombok.Data;

@Data
public class ReviewRequest {

    private Long counselorId;
    private String reservationReview;
    private Float reservationScore;

    public static ReviewRequest reviewRequest(Long counselorId, String reservationReview, Float reservationScore){
        ReviewRequest reviewRequest = new ReviewRequest();

        reviewRequest.counselorId = counselorId;
        reviewRequest.reservationReview = reservationReview;
        reviewRequest.reservationScore = reservationScore;

        return reviewRequest;
    }
}
