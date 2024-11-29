package com.yours.emong.domain.chat.domain.room.mapper;

import com.yours.emong.domain.chat.domain.room.ChatRoomEntity;
import com.yours.emong.domain.chat.domain.room.dto.ChatRoom;

public class ChatRoomMapper {

    public static ChatRoomEntity toEntity(ChatRoom chatRoom) {
        return ChatRoomEntity.builder()
                .id(chatRoom.getId())
                .title(chatRoom.getTitle())
                .createDate(chatRoom.getCreateDate())
                .build();
    }

    public static ChatRoom toChatRoom(ChatRoomEntity chatRoomEntity) {
        return ChatRoom.builder()
                .id(chatRoomEntity.getId())
                .title(chatRoomEntity.getTitle())
                .createDate(chatRoomEntity.getCreateDate())
                .build();
    }
}
