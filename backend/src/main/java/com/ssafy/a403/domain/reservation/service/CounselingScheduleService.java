package com.ssafy.a403.domain.reservation.service;

import com.ssafy.a403.domain.reservation.dto.UpdateScheduleRequest;
import com.ssafy.a403.domain.reservation.entity.CounselingSchedule;
import com.ssafy.a403.domain.reservation.repository.CounselingScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CounselingScheduleService {

    private final CounselingScheduleRepository counselingScheduleRepository;

    /**
     * 상담 가능 시간 조회
     * @param memberNo
     * @return
     */
    public CounselingSchedule findByCounselorNo(Long memberNo) {
        return counselingScheduleRepository.findByCounselorNo(memberNo).
                orElseThrow(EntityNotFoundException::new);
    }

    /**
     * 상담 가능 시간 초기 생성
     * @param counselingSchedule
     * @return
     */
    @Transactional
    public Long createSchedule(CounselingSchedule counselingSchedule) {
        counselingScheduleRepository.save(counselingSchedule);
        return counselingSchedule.getNo();
    }

    /**
     * 상담 가능 시간 변경
     */
    public CounselingSchedule updateSchedule(UpdateScheduleRequest request) {
        CounselingSchedule schedule = counselingScheduleRepository.findByCounselorNo(request.getCounselorNo()).
                orElseThrow(EntityNotFoundException::new);
        schedule.updateCounselingSchedule(request);

        return counselingScheduleRepository.findByCounselorNo(request.getCounselorNo()).
                orElseThrow(EntityNotFoundException::new);
    }
}

