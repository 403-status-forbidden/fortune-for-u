package com.ssafy.a403.domain.reservation.service;

import com.ssafy.a403.domain.member.entity.Counselor;
import com.ssafy.a403.domain.member.entity.Member;
import com.ssafy.a403.domain.member.repository.CounselorRepository;
import com.ssafy.a403.domain.member.repository.MemberRepository;
import com.ssafy.a403.domain.model.ReservationStatus;
import com.ssafy.a403.domain.reservation.dto.*;
import com.ssafy.a403.domain.reservation.entity.CounselingReservation;
import com.ssafy.a403.domain.reservation.repository.CounselingReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.*;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CounselingReservationService {

    private final CounselingReservationRepository counselingReservationRepository;
    private final MemberRepository memberRepository;
    private final CounselorRepository counselorRepository;


    //예약하기
    @Transactional
    public String saveReservation(Long memberId, Long counselorId, String reservationType, LocalDateTime reservationDate) {

        Member member = memberRepository.findById(memberId).orElseThrow(EntityNotFoundException::new);
        Counselor counselor = counselorRepository.findById(counselorId).orElseThrow(EntityNotFoundException::new);
        List<CounselingReservation> reservationList = counselingReservationRepository.findByCounselor(counselor);
        for (CounselingReservation reservation : reservationList) {
            if(reservation.getReservationDateTime().equals(reservationDate)){

                return "해당 시간에 이미 예약이 존재합니다.";
            }
        }
        if (counselor.isSelf(memberId)){
            return "자기자신은 예약할 수 없습니다.";
        }
        CounselingReservation counselingReservation = CounselingReservation.builder()
                .member(member)
                .counselor(counselor)
                .reservationType(reservationType)
                .reservationDateTime(reservationDate)
                .reservationStatus(ReservationStatus.WAITING)
                .build();

        CounselingReservation savedReservation = counselingReservationRepository.save(counselingReservation);

        return "예약이 완료 됐습니다.";
    }



    // 특정 날짜 예약 가능한 시간
    public List<AvailableDateTime> availableDateTime(Long counselorId, String date) {
        List<AvailableDateTime> reservationDatetimeList = new ArrayList<>();
        // 상담사의 모든 예약들
        List<ReservationResponse> reservationResponse = getCoReservation(counselorId, date);

        reservationResponse.sort(Comparator.comparing(ReservationResponse::getReservationDateTime));

        for (ReservationResponse reservation : reservationResponse) {
            if (reservation.getReservationStatus().equals(ReservationStatus.WAITING)) {
                reservationDatetimeList.add(new AvailableDateTime().from(reservation));
            }
        }

        return reservationDatetimeList;
    }


    // 예약 리스트
    public List<ReservationResponse> reservationList(List<CounselingReservation> reservations){
        List<ReservationResponse> reservationResponses = new ArrayList<>();
        for (CounselingReservation reservation : reservations) {
            reservationResponses.add(new ReservationResponse().from(reservation));
        }
        return reservationResponses;
    }


    // 일반회원 예약 조회
    public List<ReservationResponse> getReservation(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(EntityNotFoundException::new);
        List<CounselingReservation> reservations = counselingReservationRepository.findByMember(member);
        if (reservations.isEmpty()) {
            return Collections.emptyList();
        }
        reservations.sort(Comparator.comparing(CounselingReservation::getReservationNo).reversed());
        return reservationList(reservations);
    }



    // 종료된 상담 리스트 (결과 목록 보기)
    public List<ReportsListResponse> getReportsList(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(EntityNotFoundException::new);
        List<CounselingReservation> reservations = counselingReservationRepository.findByMember(member);

        List<ReportsListResponse> reportsListResponses = new ArrayList<>();
        for (CounselingReservation reservation : reservations) {
            if (reservation.getReservationStatus().equals(ReservationStatus.END)){
                System.out.println(reservation.getReservationStatus());
                reportsListResponses.add(new ReportsListResponse().from(reservation));
            }
        }
        return reportsListResponses;
    }


    // Waiting 상태의 상담 갯수
    public Long countWaitingList(Long counselorId) {
        Counselor counselor = counselorRepository.findById(counselorId).orElseThrow(EntityNotFoundException::new);
        List<CounselingReservation> reservations = counselingReservationRepository.findByCounselor(counselor);
        if (reservations.isEmpty()) {
            return 0L;
        }
        return reservations.stream()
                .filter(reservation -> reservation.getReservationStatus() == ReservationStatus.WAITING)
                .count();

    }


    // 상담가 예약 조회
    public List<ReservationResponse> getCoReservation(Long counselorId, String date) {
        Counselor counselor = counselorRepository.findById(counselorId).orElseThrow(EntityNotFoundException::new);
        List<CounselingReservation> reservations = counselingReservationRepository.findByCounselor(counselor);
        if (reservations.isEmpty()) {
            return Collections.emptyList();
        }
        reservations.sort(Comparator.comparing(CounselingReservation::getReservationDateTime).reversed());

        List<ReservationResponse> reservationResponses = new ArrayList<>();
        for (CounselingReservation reservation : reservations) {
            if (reservation.getReservationDateTime().toLocalDate().toString().equals(date)) {
                reservationResponses.add(new ReservationResponse().from(reservation));
            }
        }
        return reservationResponses;
    }



    // 예약 번호로 예약 조회
    public ReservationResponse checkReservation(Long reservationNo) {
        CounselingReservation counselingReservation = counselingReservationRepository.findById(reservationNo).orElseThrow(EntityNotFoundException::new);
        return new ReservationResponse().from(counselingReservation);
    }



    //예약 취소
    @Transactional
    public String cancelReservation(Long reservationNo) {
       CounselingReservation counselingReservation = counselingReservationRepository.findById(reservationNo).orElseThrow(EntityNotFoundException::new);

       return counselingReservation.cancel();
    }



    // 상담 결과 상세 조회
    public ReportDetailResponse getReportDetail(Long reservationNo) {
        CounselingReservation counselingReservation = counselingReservationRepository.findById(reservationNo).orElseThrow(EntityNotFoundException::new);
        return new ReportDetailResponse().from(counselingReservation);
    }




    // 후기 작성
    @Transactional
    public String postReview(Long reservationNo, Long memberId, String review, Float rez_score) {
        CounselingReservation counselingReservation = counselingReservationRepository.findById(reservationNo).orElseThrow(EntityNotFoundException::new);

        if (!counselingReservation.checkEmpty()) {
            throw new IllegalArgumentException("Review already written");
        }
        if (!counselingReservation.checkMemberId(memberId)) {
            throw new IllegalArgumentException("Member does not match");
        }
        if (!counselingReservation.checkStatus()) {
            throw new IllegalArgumentException("Counseling is not finished");
        }

        return counselingReservation.saveReview(review, rez_score);

    }



    // 후기 삭제
    @Transactional
    public void deleteReview(Long reservationNo) {
        CounselingReservation counselingReservation = counselingReservationRepository.findById(reservationNo).orElseThrow(EntityNotFoundException::new);

        if (counselingReservation != null) {
            counselingReservation.deleteReview();
        }
    }


    // 후기 리스트
    public List<ReviewResponse> reviewList(List<CounselingReservation> reservations){
        List<ReviewResponse> reviewResponses = new ArrayList<>();
        for ( CounselingReservation reservation : reservations) {

            if (reservation.getReservationReview() != null){
                reviewResponses.add(new ReviewResponse().from(reservation));
            }
        }
        if (reviewResponses.isEmpty()) {
            return Collections.emptyList();
        }
        return reviewResponses;
    }


    // 일반회원 후기 조회
    public List<ReviewResponse> getReview(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(EntityNotFoundException::new);

        List<CounselingReservation> reservations = counselingReservationRepository.findByMember(member);
        if (reservations.isEmpty()) {
            return Collections.emptyList();
        }
        return reviewList(reservations);
    }



    // 상담사 후기 조회
    public List<ReviewResponse> getCoReview(Long counselorId){
        Counselor counselor = counselorRepository.findById(counselorId).orElseThrow(EntityNotFoundException::new);

        List<CounselingReservation> reservations = counselingReservationRepository.findByCounselor(counselor);
        if (reservations.isEmpty()) {
            return Collections.emptyList();
        }
        return reviewList(reservations);
    }

    // gpt 결과 -> 상담사 수정 대기 처리
    @Transactional
    public void handleGptResult(Long reservationNo, String gptResult){
        CounselingReservation counselingReservation = counselingReservationRepository.findById(reservationNo).
                orElseThrow(EntityNotFoundException::new);

        counselingReservation.changeReportStatusToWaiting();
        counselingReservation.saveGptResult(gptResult);
    }

    // 상담 리포트(결과) 조회
    public ReportResponse getReport(Long reservationNo) {
        CounselingReservation counselingReservation = counselingReservationRepository.findById(reservationNo).
                orElseThrow(EntityNotFoundException::new);
        return ReportResponse.of(counselingReservation);
    }

    // gpt 결과 상담사 수정
    @Transactional
    public void updateResult(Long id, UpdateResultRequest updatedResult) {
        CounselingReservation counselingReservation = counselingReservationRepository.findById(id).
                orElseThrow(EntityNotFoundException::new);

        counselingReservation.changeReportStatusToComplete();
        counselingReservation.updateReport(updatedResult);
    }


}



