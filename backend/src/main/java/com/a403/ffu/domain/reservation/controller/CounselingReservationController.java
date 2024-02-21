package com.ssafy.a403.domain.reservation.controller;

import com.ssafy.a403.domain.member.entity.Counselor;
import com.ssafy.a403.domain.member.repository.CounselorRepository;
import com.ssafy.a403.domain.reservation.dto.*;
import com.ssafy.a403.domain.reservation.service.CounselingReservationService;
import com.ssafy.a403.global.config.security.LoginUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/reservations")
public class CounselingReservationController {

    private final CounselingReservationService counselingReservationService;

    //예약 가능 시간 조회
    @GetMapping("/availabledate/{counselorNo}/{date}")
    public List<AvailableDateTime> availableDate(@PathVariable Long counselorNo, @PathVariable String date) {
        return counselingReservationService.availableDateTime(counselorNo, date);
    }



    //예약 db에 저장
    @PostMapping("/reserve")
    public ResponseEntity<String> reserve(@AuthenticationPrincipal LoginUser loginUser,
                          @RequestBody ReservationRequest reservationRequest){

        try {
            Long memberId = loginUser.getMember().getNo();
            Long counselorId = reservationRequest.getCounselorId();
            String reservationType = reservationRequest.getReservationType();
            LocalDateTime reservationDate = reservationRequest.getReservationDate();

            String reservation = counselingReservationService.saveReservation(memberId, counselorId, reservationType, reservationDate);
            return ResponseEntity.ok(reservation);
        }catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }


    // 일반회원 id로 예약 조회
    @GetMapping("/member_rez_info")
    public List<ReservationResponse> getRezInfo(@AuthenticationPrincipal LoginUser loginUser){
        Long memberId = loginUser.getMember().getNo();
        return  counselingReservationService.getReservation(memberId);

    }

    // 일반회원 종료된 상담 리스트 조회
    @GetMapping("/member/reports")
    public List<ReportsListResponse> getReportsList(@AuthenticationPrincipal LoginUser loginUser) {
        Long memberId = loginUser.getMember().getNo();

        return counselingReservationService.getReportsList(memberId);
    }


    // 대기중인 상담 개수 조회
    @GetMapping("/counselor_waiting")
    public Long countWaiting(@AuthenticationPrincipal LoginUser loginUser) {
        Long counselorId = loginUser.getMember().getCounselor().getNo();
        return counselingReservationService.countWaitingList(counselorId);
    }


    // 상담가 id로 예약 조회
    @GetMapping("/counselor_rez_info/{date}")
    public List<ReservationResponse> getCounselorRezInfo(@AuthenticationPrincipal LoginUser loginUser, @PathVariable String date){
        Long counselorId = loginUser.getMember().getCounselor().getNo();
        return counselingReservationService.getCoReservation(counselorId, date);

    }


    // 예약 번호로 예약 조회
    @GetMapping("/reservations/{reservationNo}")
    public ReservationResponse getReservations(@PathVariable Long reservationNo) {

        return counselingReservationService.checkReservation(reservationNo);

    }


    //예약 취소
    @PatchMapping("/cancel/{reservationNo}")
    public String cancel(@PathVariable Long reservationNo) {
        return counselingReservationService.cancelReservation(reservationNo);
    }


    // 상담 결과 상세 조회
    @GetMapping("/report/{reservationNo}")
    public ReportDetailResponse getReportDetail(@PathVariable Long reservationNo) {
        return counselingReservationService.getReportDetail(reservationNo);
    }



    //후기 작성
    @PostMapping("/{reservationNo}/save_review")
    public String saveReview(@RequestBody ReviewRequest reviewRequest,
                             @PathVariable Long reservationNo,
                             @AuthenticationPrincipal LoginUser loginUser){
        Long memberId = loginUser.getMember().getNo();
        String review = reviewRequest.getReservationReview();
        Float rez_score = reviewRequest.getReservationScore();
        return counselingReservationService.postReview(reservationNo, memberId, review, rez_score);

    }


    // 후기 삭제
    @PatchMapping("/{reservationNo}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reservationNo) {
        try {
            counselingReservationService.deleteReview(reservationNo);
            return ResponseEntity.ok("리뷰 삭제 성공했습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    // 일반회원 후기 조회
    @GetMapping("/reviews")
    public List<ReviewResponse> getReview(@AuthenticationPrincipal LoginUser loginUser) {
        Long memberId = loginUser.getMember().getNo();
        return counselingReservationService.getReview(memberId);

    }


    //상담가 후기 조회(타인이 상담사 후기 조회)
    @GetMapping("/{counselorId}/co_reviews")
    public List<ReviewResponse> getCoReview(@PathVariable Long counselorId) {

        return counselingReservationService.getCoReview(counselorId);
    }

    // 상담사 후기 조회(본인)
    @GetMapping("/co_reviews")
    public List<ReviewResponse> getCoReview(@AuthenticationPrincipal LoginUser loginUser) {
        Long counselorId = loginUser.getMember().getCounselor().getNo();
        return counselingReservationService.getCoReview(counselorId);
    }

    // 상담 결과 조회
    @GetMapping("reports/{reservationNo}")
    public ResponseEntity<ReportResponse> getReport(@PathVariable Long reservationNo) {
        return ResponseEntity.ok(counselingReservationService.getReport(reservationNo));
    }

    // 상담 결과(gptResult) 수정
    @PutMapping("/counseling_results/{reservationNo}")
    public ResponseEntity<String> updateCounselingResult(@PathVariable Long reservationNo,
                                                         @RequestBody UpdateResultRequest updatedResult) {
        counselingReservationService.updateResult(reservationNo, updatedResult);
        return ResponseEntity.ok("게시글이 성공적으로 수정됨");
    }
}
