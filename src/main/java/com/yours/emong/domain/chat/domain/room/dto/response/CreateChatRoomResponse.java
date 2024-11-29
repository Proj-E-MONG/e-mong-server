package com.yours.emong.domain.chat.domain.room.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateChatRoomResponse {
    private Long id;
    private String title;
}
