package com.ssafy.a403.domain.reservation.controller;

import com.ssafy.a403.domain.reservation.dto.ScheduleResponse;
import com.ssafy.a403.domain.reservation.dto.UpdateScheduleRequest;
import com.ssafy.a403.domain.reservation.entity.CounselingSchedule;
import com.ssafy.a403.domain.reservation.service.CounselingScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class CounselingScheduleController {

    private final CounselingScheduleService counselingScheduleService;


    /**
     * 상담 가능 시간 조회
     */
    @GetMapping("/api/counselors/time/{counselorNo}")
    public ResponseEntity<ScheduleResponse> getCounselingSchedule(@PathVariable("counselorNo") Long counselorNo) {
        CounselingSchedule scheduleResponse = counselingScheduleService.getSchedule(counselorNo);
        return ResponseEntity.ok(ScheduleResponse.from(scheduleResponse));
    }

    /**
     * 상담 가능 시간 수정
     */
    @PatchMapping("/api/counselors/time/update/{counselorNo}")
    public ResponseEntity<ScheduleResponse> updateCounselingSchedule(
            @PathVariable("counselorNo") Long counselorNo,
            @RequestBody UpdateScheduleRequest updateScheduleRequest) {
        CounselingSchedule scheduleResponse = counselingScheduleService.updateSchedule(counselorNo, updateScheduleRequest);
        return ResponseEntity.ok(ScheduleResponse.from(scheduleResponse));
    }
}
