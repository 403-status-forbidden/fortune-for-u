package com.ssafy.a403.domain.reservation.repository;
import com.ssafy.a403.domain.member.entity.Counselor;
import com.ssafy.a403.domain.member.entity.Member;
import com.ssafy.a403.domain.model.ReservationStatus;
import com.ssafy.a403.domain.reservation.entity.CounselingReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CounselingReservationRepository extends JpaRepository<CounselingReservation, Long> {

    List<CounselingReservation> findByMember(Member member);

    List<CounselingReservation> findByCounselor(Counselor counselor);

    CounselingReservation findBySessionId(String sessionId);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE CounselingReservation cr set cr.sessionId = :sessionId, cr.reservationStatus = :reservationStatus where cr.reservationNo = :reservationNo")
    void saveRoom(@Param("sessionId")String sessionId, @Param("reservationStatus")ReservationStatus reservationStatus, @Param("reservationNo")Long reservationNo);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE CounselingReservation cr set cr.sessionId = null, cr.reservationStatus = :reservationStatus, cr.reservationRecorded = :recordingUrl where cr.reservationNo = :reservationNo")
    void saveCloseRoom(@Param("reservationStatus")ReservationStatus reservationStatus, @Param("recordingUrl")String recordingUrl, @Param("reservationNo")Long reservationNo);
}
