package com.yours.emong.domain.chat.domain.room.repository;

import com.yours.emong.domain.chat.domain.room.ChatRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity, Long> {
}
