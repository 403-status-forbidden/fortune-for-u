package com.ssafy.a403.domain.counselorform.repository;

import com.ssafy.a403.domain.counselorform.entity.CounselorForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CounselorFormRepository extends JpaRepository<CounselorForm, Long>, CounselorFormRepositoryCustom {

}
