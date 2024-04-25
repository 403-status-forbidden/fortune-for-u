package com.a403.ffu.reservation.entity;

import com.a403.ffu.member.entity.Counselor;
import com.a403.ffu.reservation.dto.UpdateScheduleRequest;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class CounselingSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_no")
    private Long no;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "counselor_no")
    private Counselor counselor;


    private String mon;

    private String tue;

    private String wed;

    private String thu;

    private String fri;

    private String sat;

    private String sun;

    @Builder
    public CounselingSchedule(Counselor counselor, String mon, String tue, String wed, String thu,
            String fri, String sat, String sun) {
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
        this.mon = updateScheduleRequest.mon();
        this.tue = updateScheduleRequest.tue();
        this.wed = updateScheduleRequest.wed();
        this.thu = updateScheduleRequest.thu();
        this.fri = updateScheduleRequest.fri();
        this.sat = updateScheduleRequest.sat();
        this.sun = updateScheduleRequest.sun();
    }
}

