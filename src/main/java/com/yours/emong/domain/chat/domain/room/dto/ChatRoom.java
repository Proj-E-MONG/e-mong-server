package com.yours.emong.domain.chat.domain.room.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ChatRoom {

    private Long id;
    private String title;
    private LocalDateTime createDate;


}
