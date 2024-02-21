package com.ssafy.a403.domain.admin.controller;

import com.ssafy.a403.domain.admin.service.AdminService;
import com.ssafy.a403.domain.counselorform.dto.CounselorFormDetailsResponse;
import com.ssafy.a403.domain.counselorform.dto.CounselorFormResponse;
import com.ssafy.a403.domain.counselorform.dto.CounselorFormUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/api/admin")
@RestController
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/counselor-forms")
    public ResponseEntity<Page<CounselorFormResponse>> counselorForms(@RequestParam String filter, Pageable pageable){
        log.trace(filter);
        return new ResponseEntity<>(adminService.counselorFormList(filter, pageable), HttpStatus.OK);
    }

    @GetMapping("/counselor-forms/{counselorFormNo}")
    public ResponseEntity<CounselorFormDetailsResponse> counselorFormDetails(@PathVariable Long counselorFormNo) {

        return new ResponseEntity<>(adminService.counselorFormDetail(counselorFormNo), HttpStatus.OK);
    }

    @PatchMapping("/counselor-forms/{counselorFormNo}/update")
    public HttpStatus updatePassState(@PathVariable Long counselorFormNo, @RequestBody CounselorFormUpdateRequest updateRequest) {

        adminService.updatePassState(counselorFormNo, updateRequest);
        return HttpStatus.NO_CONTENT;
    }
}
