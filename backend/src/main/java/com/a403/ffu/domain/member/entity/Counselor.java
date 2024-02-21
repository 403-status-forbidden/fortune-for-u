package com.ssafy.a403.domain.member.entity;

import com.ssafy.a403.domain.member.dto.UpdateCounselorRequest;
import com.ssafy.a403.domain.model.CounselorType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

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

    private String career;

    private String address;

    private String phone;

    @ColumnDefault("0")
    private Integer reviewCnt;

    @ColumnDefault("0.0")
    private Float ratingAvg;

    private String startTime;

    private String endTime;


    @Builder
    public Counselor(Long no, Member member, CounselorType counselorType, String major, String intro,
                     String address, String phone, String startTime, String endTime,
                    String career) {
        this.no = no;
        this.member = member;
        this.counselorType = counselorType;
        this.major = major;
        this.intro = intro;
        this.career = career;
        this.address = address;
        this.phone = phone;
        this.startTime = startTime;
        this.endTime = endTime;
        this.reviewCnt = 0;
        this.ratingAvg = 0.0F;
    }


    public void updateCounselorInfo(UpdateCounselorRequest request) {
        this.counselorType = request.getCounselorType();
        this.major = request.getMajor();
        this.intro = request.getIntro();
        this.career = request.getCareer();
        this.address = request.getAddress();
        this.phone = request.getPhone();
        this.startTime = request.getStartTime();
        this.endTime = request.getEndTime();
    }

    public void updateCounselorReview(Float rating) {
        this.ratingAvg = (this.ratingAvg * this.reviewCnt + rating) / (reviewCnt + 1);
        this.reviewCnt += 1;
    }

    public boolean isSelf(Long memberId) {
        return member.getNo().equals(memberId);
    }

}
