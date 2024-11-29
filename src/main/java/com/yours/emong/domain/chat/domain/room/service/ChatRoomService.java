package com.yours.emong.domain.chat.domain.room.service;

import com.yours.emong.domain.chat.domain.room.ChatRoomEntity;
import com.yours.emong.domain.chat.domain.room.dto.request.CreateChatRoomRequest;
import com.yours.emong.domain.chat.domain.room.dto.response.CreateChatRoomResponse;
import com.yours.emong.domain.chat.domain.room.repository.ChatRoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    @Transactional
    public CreateChatRoomResponse createChatRoom(CreateChatRoomRequest request) {
        ChatRoomEntity chatRoomEntity = ChatRoomEntity
                .builder()
                .title(request.getTitle())
                .build();

        chatRoomRepository.save(chatRoomEntity);
        return new CreateChatRoomResponse(
                chatRoomEntity.getId(),
                chatRoomEntity.getTitle()
        );
    }

}
