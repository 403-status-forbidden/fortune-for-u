package com.a403.ffu.counselorform.entity;

import com.a403.ffu.counselorform.dto.CounselorFormUpdateRequest;
import com.a403.ffu.global.audit.BaseTime;
import com.a403.ffu.member.entity.Counselor;
import com.a403.ffu.member.entity.Member;
import com.a403.ffu.model.CounselorType;
import com.a403.ffu.model.PassState;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CounselorForm extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "conselor_form_no")
    private Long no;

    @ManyToOne
    @JoinColumn(name = "member_no")
    private Member member;

    @Enumerated(EnumType.STRING)
    private CounselorType counselorType;

    private String major;

    private String career;

    private String intro;

    private String address;

    private String phone;

    @Enumerated(EnumType.STRING)
    private PassState passState;

    private String reason;

    @Builder
    public CounselorForm(Member member, CounselorType counselorType, String major, String career,
            String intro, String address, String phone, PassState passState,
            LocalDateTime submitTime, String reason) {
        this.member = member;
        this.counselorType = counselorType;
        this.major = major;
        this.career = career;
        this.intro = intro;
        this.address = address;
        this.phone = phone;
        this.passState = passState;
        this.reason = reason;
    }

    public Counselor toCounselor() {
        return Counselor.builder()
                .member(this.member)
                .counselorType(this.counselorType)
                .major(this.major)
                .intro(this.intro)
                .career(this.career)
                .address(this.address)
                .phone(this.phone)
                .startTime("09:00")
                .endTime("18:00")
                .build();
    }

    public void changeFormStatus(CounselorFormUpdateRequest updateRequest) {
        this.passState = updateRequest.getPassState();
        this.reason = updateRequest.getReason();
    }
}
