package com.ssafy.a403.domain.reservation.repository;

import com.ssafy.a403.domain.reservation.entity.CounselingSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CounselingScheduleRepository extends JpaRepository<CounselingSchedule, Long> {
    Optional<CounselingSchedule> findByCounselorNo(Long counselorNo);
}
