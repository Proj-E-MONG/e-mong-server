package com.yours.emong.domain.notice.controller;


import com.yours.emong.domain.notice.dto.NoticeCreateRequestDTO;
import com.yours.emong.domain.notice.dto.NoticeUpdateRequestDTO;
import com.yours.emong.domain.notice.entity.NoticeEntity;
import com.yours.emong.domain.notice.service.NoticeService;
import com.yours.emong.global.common.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chatrooms/{chatRoomId}/notices")
public class NoticeController {

    private final NoticeService noticeService;

    // 공지 생성
    @PostMapping
    public ResponseEntity<NoticeEntity> createNotice(@RequestBody NoticeCreateRequestDTO requestDto, @PathVariable Long chatRoomId) {
        NoticeEntity notice = noticeService.createNotice(requestDto, chatRoomId);
        return new ResponseEntity<>(notice, HttpStatus.CREATED);
    }

    // 공지 수정
    @PutMapping
    public ResponseEntity<NoticeEntity> updateNotice(@RequestBody NoticeUpdateRequestDTO requestDto) {
        NoticeEntity updatedNotice = noticeService.updateNotice(requestDto);
        return new ResponseEntity<>(updatedNotice, HttpStatus.OK);
    }
}
