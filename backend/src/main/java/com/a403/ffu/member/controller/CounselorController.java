package com.a403.ffu.member.controller;

import com.a403.ffu.global.security.LoginUser;
import com.a403.ffu.member.dto.CounselorInfoResponse;
import com.a403.ffu.member.dto.UpdateCounselorRequest;
import com.a403.ffu.member.dto.UpdateCounselorResponse;
import com.a403.ffu.member.entity.Counselor;
import com.a403.ffu.member.service.CounselorService;
import com.a403.ffu.model.CounselorType;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CounselorController {

    private final CounselorService counselorService;

    /**
     * 상담사 정보 조회(본인)
     */
    @GetMapping("/api/counselors/info")
    public ResponseEntity<CounselorInfoResponse> getCounselorInfo(
            @AuthenticationPrincipal LoginUser loginUser) {
        Counselor counselor = counselorService.findCounselor(loginUser.getMember());
        return ResponseEntity.ok(CounselorInfoResponse.from(counselor));
    }

    /**
     * 상담사 정보 조회
     */
    @GetMapping("/api/counselors/{counselorNo}")
    public ResponseEntity<CounselorInfoResponse> getCounselorInfo(
            @PathVariable("counselorNo") Long counselorNo) {
        Counselor counselor = counselorService.findById(counselorNo);
        return ResponseEntity.ok(CounselorInfoResponse.from(counselor));
    }

    /**
     * 상담사 전체 조회
     */
    @GetMapping("/api/counselors")
    public ResponseEntity<List<CounselorInfoResponse>> getCounselorInfoList() {
        List<Counselor> findCounselors = counselorService.findCounselors();
        List<CounselorInfoResponse> counselorInfoList = findCounselors.stream()
                .map(CounselorInfoResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(counselorInfoList);
    }

    /**
     * 상담사 평점 순 조회
     */
    @GetMapping("/api/counselors/by_ratings")
    public ResponseEntity<Page<CounselorInfoResponse>> getCounselorInfoByRating(
            @RequestParam CounselorType counselorType,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size) {
        Page<Counselor> counselorPage = counselorService.findByRating(counselorType, page, size);
        Page<CounselorInfoResponse> counselorResponsePage = counselorPage
                .map(CounselorInfoResponse::from);
        return ResponseEntity.ok(counselorResponsePage);
    }

    /**
     * 상담사 후기 순 조회
     */
    @GetMapping("/api/counselors/by_reviews")
    public ResponseEntity<Page<CounselorInfoResponse>> getCounselorInfoByReview(
            @RequestParam CounselorType counselorType,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size) {
        Page<Counselor> counselorPage = counselorService.findByReview(counselorType, page, size);
        Page<CounselorInfoResponse> counselorResponsePage = counselorPage
                .map(CounselorInfoResponse::from);
        return ResponseEntity.ok(counselorResponsePage);
    }

    /**
     * 상담사 정보 변경
     */
    @PatchMapping("/api/counselors/update")
    public ResponseEntity<UpdateCounselorResponse> updateCounselorInfo(
            @AuthenticationPrincipal LoginUser loginUser,
            @RequestBody UpdateCounselorRequest request) {
        counselorService.updateCounselorInfo(loginUser.getMember(), request);
        Counselor counselor = counselorService.findCounselor(loginUser.getMember());
        return ResponseEntity.ok(new UpdateCounselorResponse(counselor.getNo()));
    }

}
