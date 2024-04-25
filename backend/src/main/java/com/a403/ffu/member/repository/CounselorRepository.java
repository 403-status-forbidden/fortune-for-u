package com.a403.ffu.member.repository;

import com.a403.ffu.member.entity.Counselor;
import com.a403.ffu.model.CounselorType;
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
