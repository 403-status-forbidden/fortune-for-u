package com.ssafy.a403.domain.counselorform.repository;

import com.ssafy.a403.domain.counselorform.entity.CounselorForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CounselorFormRepositoryCustom {

    Page<CounselorForm> findAllPaging(String filter, Pageable pageable);
}
