package com.a403.ffu.member.dto;


import jakarta.validation.constraints.NotNull;

public record AuthRequest(@NotNull String authToken) {

}
