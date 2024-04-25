package com.a403.ffu.room.service;

import com.a403.ffu.model.ReservationStatus;
import com.a403.ffu.reservation.entity.CounselingReservation;
import com.a403.ffu.reservation.repository.CounselingReservationRepository;
import com.a403.ffu.room.dto.RoomRequest;
import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final CounselingReservationRepository counselingReservationRepository;

    @Transactional
    public Optional<CounselingReservation> saveRoom(RoomRequest roomRequest, String sessionId) {

        counselingReservationRepository.saveRoom(
                sessionId, ReservationStatus.PROCEEDING, roomRequest.getReservationNo());

        return counselingReservationRepository.findById(roomRequest.getReservationNo());

    }

    public CounselingReservation findBySessionId(String sessionId) {

        return counselingReservationRepository.findBySessionId(sessionId);

    }

    @Transactional
    public boolean updateSessionIdAndRecordingUrl(Long reservationNo, String recordingUrl) {

        counselingReservationRepository.saveCloseRoom(
                ReservationStatus.END, recordingUrl, reservationNo);

        Optional<CounselingReservation> counselingReservation =
                counselingReservationRepository.findById(reservationNo);

//        if(counselingReservation.get().getReservationRecorded() == null || counselingReservation.get().getSessionId() != null){
//            return false;
//        }
        return true;
    }
}