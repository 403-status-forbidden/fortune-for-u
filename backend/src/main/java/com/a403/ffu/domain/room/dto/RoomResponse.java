package com.ssafy.a403.domain.room.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RoomResponse {

    String sessionId;

    @Builder
    public RoomResponse(String sessionId) {
        this.sessionId = sessionId;
    }
}
