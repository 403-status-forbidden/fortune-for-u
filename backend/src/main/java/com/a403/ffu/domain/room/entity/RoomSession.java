package com.ssafy.a403.domain.room.entity;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Slf4j
@NoArgsConstructor(force = true)
@Entity
public class RoomSession {

    @Id
    private final String roomId;

    @Builder
    public RoomSession(String roomId){
        this.roomId = roomId;
    }

}
