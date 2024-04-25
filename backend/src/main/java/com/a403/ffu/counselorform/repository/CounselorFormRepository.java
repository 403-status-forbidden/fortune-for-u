package com.a403.ffu.counselorform.repository;

import com.a403.ffu.counselorform.entity.CounselorForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CounselorFormRepository extends JpaRepository<CounselorForm, Long>, CounselorFormRepositoryCustom {

}
