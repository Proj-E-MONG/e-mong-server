package com.yours.emong.domain.chat.domain.room.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class CreateChatRoomRequest {

    private String title; //채팅방 명
}
