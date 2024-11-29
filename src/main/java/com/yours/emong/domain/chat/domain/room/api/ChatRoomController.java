package com.yours.emong.domain.chat.domain.room.api;

import com.yours.emong.domain.chat.domain.room.dto.ChatRoom;
import com.yours.emong.domain.chat.domain.room.dto.request.CreateChatRoomRequest;
import com.yours.emong.domain.chat.domain.room.dto.response.CreateChatRoomResponse;
import com.yours.emong.domain.chat.domain.room.service.ChatRoomService;
import com.yours.emong.global.common.response.BaseResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chatRoom")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @PostMapping("/create")
    public BaseResponseData<CreateChatRoomResponse> createChatRoom(
            @RequestBody CreateChatRoomRequest request) {
        return BaseResponseData.ok("채팅방이 생성되었습니다.", chatRoomService.createChatRoom(request));
    }

//    @GetMapping("/chatList")
//    public BaseResponseData<List<ChatRoom>> getChatRoomList() {
//
//    }
}
