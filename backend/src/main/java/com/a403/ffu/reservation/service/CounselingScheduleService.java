package com.a403.ffu.reservation.service;

import com.a403.ffu.reservation.dto.UpdateScheduleRequest;
import com.a403.ffu.reservation.repository.CounselingScheduleRepository;
import com.a403.ffu.reservation.entity.CounselingSchedule;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CounselingScheduleService {

    private final CounselingScheduleRepository counselingScheduleRepository;

    /**
     * 상담 가능 시간 조회
     */
    public CounselingSchedule getSchedule(Long memberNo) {
        return counselingScheduleRepository.findByCounselorNo(memberNo).
                orElseThrow(EntityNotFoundException::new);
    }

    /**
     * 상담 가능 시간 초기 생성
     */
    @Transactional
    public Long createSchedule(CounselingSchedule counselingSchedule) {
        counselingScheduleRepository.save(counselingSchedule);
        return counselingSchedule.getNo();
    }

    /**
     * 상담 가능 시간 변경
     */
    @Transactional
    public CounselingSchedule updateSchedule(Long counselorNo, UpdateScheduleRequest request) {
        CounselingSchedule schedule = counselingScheduleRepository.findByCounselorNo(counselorNo).
                orElseThrow(EntityNotFoundException::new);

        schedule.updateCounselingSchedule(request);

        counselingScheduleRepository.save(schedule);

        return counselingScheduleRepository.findByCounselorNo(counselorNo).
                orElseThrow(EntityNotFoundException::new);
    }
}

