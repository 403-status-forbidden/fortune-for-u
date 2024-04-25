package com.a403.ffu.reservation.repository;

import com.a403.ffu.reservation.entity.CounselingSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CounselingScheduleRepository extends JpaRepository<CounselingSchedule, Long> {
    Optional<CounselingSchedule> findByCounselorNo(Long counselorNo);
}
