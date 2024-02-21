package com.ssafy.a403.domain.member.repository;

import com.ssafy.a403.domain.member.entity.Counselor;
import com.ssafy.a403.domain.model.CounselorType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CounselorRepository extends JpaRepository<Counselor, Long> {

    // 후기 순 조회
    Page<Counselor> findAllByCounselorTypeInOrderByReviewCntDesc(List<CounselorType> counselorTypes, Pageable pageable);

    // 평점 순 조회
    Page<Counselor> findAllByCounselorTypeInOrderByRatingAvgDesc(List<CounselorType> counselorTypes, Pageable pageable);
}
