package com.a403.ffu.member.entity;

import com.a403.ffu.member.dto.UpdateCounselorRequest;
import com.a403.ffu.model.CounselorType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;


@Entity
@Getter
@NoArgsConstructor
public class Counselor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "counselor_no")
    private Long no;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_no")
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
    public Counselor(Long no, Member member, CounselorType counselorType, String major,
            String intro,
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
        this.counselorType = request.counselorType();
        this.major = request.major();
        this.intro = request.intro();
        this.career = request.career();
        this.address = request.address();
        this.phone = request.phone();
        this.startTime = request.startTime();
        this.endTime = request.endTime();
    }

    public void updateCounselorReview(Float rating) {
        this.ratingAvg = (this.ratingAvg * this.reviewCnt + rating) / (reviewCnt + 1);
        this.reviewCnt += 1;
    }

    public boolean isSelf(Long memberId) {
        return member.getNo().equals(memberId);
    }

}
