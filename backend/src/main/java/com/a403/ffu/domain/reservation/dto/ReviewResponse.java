package com.ssafy.a403.domain.reservation.dto;


import com.ssafy.a403.domain.reservation.entity.CounselingReservation;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class ReviewResponse {

    private Long reservationNo;
    private Long counselorId;
    private String memberName;
    private String counselorName;
    private String profileImage;
    private Float ratingAvg;
    private Float reservationScore;
    private String review;

    public ReviewResponse from(CounselingReservation counselingReservation) {
        ReviewResponse reviewResponse = new ReviewResponse();
        reviewResponse.reservationNo = counselingReservation.getReservationNo();
        reviewResponse.counselorId = counselingReservation.getCounselor().getNo();
        reviewResponse.memberName = counselingReservation.getMember().getName();
        reviewResponse.counselorName = counselingReservation.getCounselor().getMember().getName();
        reviewResponse.review = counselingReservation.getReservationReview();
        reviewResponse.profileImage = counselingReservation.getMember().getProfileImage();
        reviewResponse.ratingAvg = counselingReservation.getCounselor().getRatingAvg();
        reviewResponse.reservationScore = counselingReservation.getReservationScore();

        return reviewResponse;

    }
}
