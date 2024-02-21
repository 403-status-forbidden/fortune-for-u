package com.ssafy.a403.domain.member.dto;



import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotNull;


@Data
public class AuthRequest {

    @NotNull
    private final String authToken;
}
