package com.a403.ffu.global.security.exception;

public record ErrorResponse(Integer code, String message) {

    public static ErrorResponse of(Integer code, String message) {
        return new ErrorResponse(code, message);
    }
}
