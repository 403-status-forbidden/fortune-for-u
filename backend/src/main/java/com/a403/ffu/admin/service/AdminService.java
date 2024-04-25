package com.a403.ffu.admin.service;

import com.a403.ffu.counselorform.dto.CounselorFormDetailsResponse;
import com.a403.ffu.counselorform.dto.CounselorFormResponse;
import com.a403.ffu.counselorform.dto.CounselorFormUpdateRequest;
import com.a403.ffu.counselorform.entity.CounselorForm;
import com.a403.ffu.counselorform.service.CounselorFormService;
import com.a403.ffu.member.service.CounselorService;
import com.a403.ffu.model.PassState;
import com.a403.ffu.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class AdminService {

    private final CounselorFormService counselorFormService;
    private final CounselorService counselorService;

    public Page<CounselorFormResponse> counselorFormList(String filter, Pageable pageable) {

        Page<CounselorFormResponse> counselorFormResponses =
                counselorFormService.getAllCounselorForms(filter, pageable)
                        .map(CounselorFormResponse::from);
        return counselorFormResponses;
    }

    public CounselorFormDetailsResponse counselorFormDetail(Long counselorFormNo) {

        CounselorForm counselorForm = counselorFormService.getCounselorForm(counselorFormNo);
        return CounselorFormDetailsResponse.of(counselorForm);
    }

    @Transactional
    public void updatePassState(Long counselorFormNo, CounselorFormUpdateRequest updateRequest) {

        counselorFormService.updatePassStatus(counselorFormNo, updateRequest);
        CounselorForm counselorForm = counselorFormService.getCounselorForm(counselorFormNo);
        // 통화 상태일 때만, Counselor 등록
        if (updateRequest.getPassState().equals(PassState.PASS)) {
            counselorService.registerCounselor(counselorForm);
            counselorForm.getMember().giveAuthority(Role.ROLE_COUNSELOR);
        }
    }
}
