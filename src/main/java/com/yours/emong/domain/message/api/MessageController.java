package com.yours.emong.domain.message.api;

import com.yours.emong.domain.message.service.MessageService;
import com.yours.emong.global.common.response.BaseResponse;
import com.yours.emong.global.common.response.BaseResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/send")
    public BaseResponse sendMessage() {
        messageService.sendMessages();
        return BaseResponseData.ok("메세지 전송 성공");
    }
}
//twilio 의 scheduler가 시간에 맞게 호출(백그라운드)