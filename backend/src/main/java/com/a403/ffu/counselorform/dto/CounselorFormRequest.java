package com.a403.ffu.counselorform.dto;

import com.a403.ffu.counselorform.entity.CounselorForm;
import com.a403.ffu.member.entity.Member;
import com.a403.ffu.model.CounselorType;
import com.a403.ffu.model.PassState;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CounselorFormRequest {

    private final CounselorType counselorType;

    private final String major;

    private final String career;

    private final String address;

    private final String phone;

    private final String intro;

    public CounselorForm toCounselorForm(Member member){

        return CounselorForm.builder()
                .counselorType(counselorType)
                .major(major)
                .career(career)
                .address(address)
                .phone(phone)
                .intro(intro)
                .member(member)
                .passState(PassState.WAITING)
                .build();
    }
}
