package com.ssafy.a403.domain.reservation.entity;

import com.ssafy.a403.domain.member.entity.Counselor;
import com.ssafy.a403.domain.reservation.dto.UpdateScheduleRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class CounselingSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_no")
    private Long no;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="counselor_no")
    private Counselor counselor;


    private String mon;

    private String tue;

    private String wed;

    private String thu;

    private String fri;

    private String sat;

    private String sun;

    @Builder
    public CounselingSchedule(Counselor counselor, String mon, String tue, String wed, String thu, String fri, String sat, String sun){
        this.counselor = counselor;
        this.mon = mon;
        this.tue = tue;
        this.wed = wed;
        this.thu = thu;
        this.fri = fri;
        this.sat = sat;
        this.sun = sun;
    }

    public void updateCounselingSchedule(UpdateScheduleRequest updateScheduleRequest) {
        this.mon = updateScheduleRequest.getMon();
        this.tue = updateScheduleRequest.getTue();
        this.wed = updateScheduleRequest.getWed();
        this.thu = updateScheduleRequest.getThu();
        this.fri = updateScheduleRequest.getFri();
        this.sat = updateScheduleRequest.getSat();
        this.sun = updateScheduleRequest.getSun();
    }
}

