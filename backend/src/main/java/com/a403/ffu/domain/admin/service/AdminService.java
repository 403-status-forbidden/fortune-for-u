package com.ssafy.a403.domain.admin.service;

import com.ssafy.a403.domain.counselorform.dto.CounselorFormDetailsResponse;
import com.ssafy.a403.domain.counselorform.dto.CounselorFormResponse;
import com.ssafy.a403.domain.counselorform.dto.CounselorFormUpdateRequest;
import com.ssafy.a403.domain.counselorform.entity.CounselorForm;
import com.ssafy.a403.domain.counselorform.service.CounselorFormService;
import com.ssafy.a403.domain.member.service.CounselorService;
import com.ssafy.a403.domain.member.service.MemberService;
import com.ssafy.a403.domain.model.PassState;
import com.ssafy.a403.domain.model.Role;
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
