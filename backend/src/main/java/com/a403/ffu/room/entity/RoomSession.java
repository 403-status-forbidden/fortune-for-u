package com.a403.ffu.room.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class RoomSession {

    @Id
    private String roomId;

    public RoomSession(String roomId) {
        this.roomId = roomId;
    }
}
