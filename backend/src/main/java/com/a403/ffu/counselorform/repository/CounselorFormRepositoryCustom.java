package com.a403.ffu.counselorform.repository;

import com.a403.ffu.counselorform.entity.CounselorForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CounselorFormRepositoryCustom {

    Page<CounselorForm> findAllPaging(String filter, Pageable pageable);
}
