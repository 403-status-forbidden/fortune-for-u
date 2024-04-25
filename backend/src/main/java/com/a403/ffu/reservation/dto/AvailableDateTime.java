package com.a403.ffu.reservation.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class AvailableDateTime {

    private Integer Hour;

    private Integer Minute;

    public AvailableDateTime from(ReservationResponse reservation){
        AvailableDateTime availableDateTime = new AvailableDateTime();
        availableDateTime.Hour = reservation.getReservationDateTime().getHour();
        availableDateTime.Minute = reservation.getReservationDateTime().getMinute();

        return availableDateTime;
    }


}
