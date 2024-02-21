package com.ssafy.a403.domain.member.service;

import com.ssafy.a403.domain.counselorform.entity.CounselorForm;
import com.ssafy.a403.domain.member.dto.UpdateCounselorRequest;
import com.ssafy.a403.domain.member.entity.Counselor;
import com.ssafy.a403.domain.member.entity.Member;
import com.ssafy.a403.domain.member.repository.CounselorRepository;
import com.ssafy.a403.domain.model.CounselorType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CounselorService {
    private final CounselorRepository counselorRepository;

    // 상담가 조회
    public Counselor findCounselor(Member member) {
        Long counselorId = member.getCounselor().getNo();
        return counselorRepository.findById(counselorId).orElseThrow(EntityNotFoundException::new);
    }

    // 상담가 조회
    public Counselor findById(Long counselorId) {
        return counselorRepository.findById(counselorId).orElseThrow(EntityNotFoundException::new);
    }

    // 상담가 전체 조회
    public List<Counselor> findCounselors() { return counselorRepository.findAll(); }

    // 상담가 평점 순 조회
    public Page<Counselor> findByRating(CounselorType counselorType, int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "ratingAvg"));
        List<CounselorType> counselorTypes = Arrays.asList(CounselorType.BOTH, counselorType);
        return counselorRepository.findAllByCounselorTypeInOrderByRatingAvgDesc(counselorTypes, pageRequest);
    }

    // 상담가 후기 순 조회
    public Page<Counselor> findByReview(CounselorType counselorType, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "reviewCnt"));
        List<CounselorType> counselorTypes = Arrays.asList(CounselorType.BOTH, counselorType);
        return counselorRepository.findAllByCounselorTypeInOrderByReviewCntDesc(counselorTypes, pageable);
    }

    // 상담사 정보 수정
    @Transactional
    public void updateCounselorInfo(Member member, UpdateCounselorRequest request) {
        Long counselorId = member.getCounselor().getNo();
        Counselor counselor = counselorRepository.findById(counselorId).
                orElseThrow(EntityNotFoundException::new);
        counselor.updateCounselorInfo(request);
        counselorRepository.save(counselor);
    }

    // 상맏사 등록
    @Transactional
    public void registerCounselor(CounselorForm counselorForm) {

        counselorRepository.save(counselorForm.toCounselor());
    }
}
