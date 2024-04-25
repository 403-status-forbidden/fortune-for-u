package com.a403.ffu.counselorform.service;

import com.a403.ffu.member.entity.Member;
import com.a403.ffu.counselorform.dto.CounselorFormRequest;
import com.a403.ffu.counselorform.dto.CounselorFormUpdateRequest;
import com.a403.ffu.counselorform.entity.CounselorForm;
import com.a403.ffu.counselorform.repository.CounselorFormRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CounselorFormService {

    private final CounselorFormRepository counselorFormRepository;

    @Transactional
    public void submitForm(CounselorFormRequest counselorFormRequest, Member member) {
        counselorFormRepository.save(counselorFormRequest.toCounselorForm(member));
    }

    @Transactional(readOnly = true)
    public Page<CounselorForm> getAllCounselorForms(String filter, Pageable pageable) {

        return counselorFormRepository.findAllPaging(filter, pageable);
    }

    @Transactional(readOnly = true)
    public CounselorForm getCounselorForm(Long counselorFormNo) {

        return counselorFormRepository.findById(counselorFormNo).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public void updatePassStatus(Long counselorFormNo, CounselorFormUpdateRequest updateRequest) {

        CounselorForm counselorForm = getCounselorForm(counselorFormNo);
        counselorForm.changeFormStatus(updateRequest);
    }
}
