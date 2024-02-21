package com.ssafy.a403.domain.room.repository;

import com.ssafy.a403.domain.room.entity.RoomSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomSessionRepository extends JpaRepository<RoomSession, String> {

}
