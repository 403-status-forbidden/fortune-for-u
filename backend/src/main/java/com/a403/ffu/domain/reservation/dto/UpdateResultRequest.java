package com.ssafy.a403.domain.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;


@Data
@Getter
@AllArgsConstructor
public class UpdateResultRequest {

    private String counselingResult;

    public UpdateResultRequest() {

    }
}