package com.ssafy.a403.domain.member.entity;

import com.ssafy.a403.domain.member.dto.UpdateCounselorRequest;
import com.ssafy.a403.domain.model.CounselorType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Counselor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "counselor_no")
    private Long no;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="member_no")
    private Member member;

    @Enumerated(EnumType.STRING)
    private CounselorType counselorType;

    private String major;

    private String intro;

    private String address;

    private String phone;

    @Builder
    public Counselor(Long no, Member member, CounselorType counselorType, String major, String intro,
                     String address, String phone) {
        this.no = no;
        this.member = member;
        this.counselorType = counselorType;
        this.major = major;
        this.intro = intro;
        this.address = address;
        this.phone = phone;
    }

    public void updateCounselorInfo(UpdateCounselorRequest request) {
        this.counselorType = request.getCounselorType();
        this.major = request.getMajor();
        this.intro = request.getIntro();
        this.address = request.getAddress();
        this.phone = request.getPhone();
    }

}
