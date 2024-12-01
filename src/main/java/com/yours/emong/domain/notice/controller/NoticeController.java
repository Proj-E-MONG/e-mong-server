package com.yours.emong.domain.notice.controller;


import com.yours.emong.domain.notice.dto.NoticeCreateRequestDTO;
import com.yours.emong.domain.notice.dto.NoticeResponseDTO;
import com.yours.emong.domain.notice.dto.NoticeUpdateRequestDTO;
import com.yours.emong.domain.notice.entity.NoticeEntity;
import com.yours.emong.domain.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chatrooms/{chatRoomId}/notices")
public class NoticeController {

    private final NoticeService noticeService;

    // 공지 생성
    @PostMapping
    public ResponseEntity<NoticeEntity> createNotice(@PathVariable Long chatRoomId, @RequestBody NoticeCreateRequestDTO requestDto) {
        requestDto.setChatRoomId(chatRoomId);
        NoticeEntity notice = noticeService.createNotice(requestDto);
        return new ResponseEntity<>(notice, HttpStatus.CREATED);
    }

    // 공지 수정
    @PutMapping
    public ResponseEntity<NoticeEntity> updateNotice(@PathVariable Long chatRoomId, @RequestBody NoticeUpdateRequestDTO requestDto) {
        NoticeEntity updatedNotice = noticeService.updateNotice(chatRoomId, requestDto); // chatRoomId 추가
        return new ResponseEntity<>(updatedNotice, HttpStatus.OK);
    }

    // 공지 삭제
    @DeleteMapping("/{noticeId}")
    public ResponseEntity<Void> deleteNotice(@PathVariable Long chatRoomId, @PathVariable Long noticeId) {
        noticeService.deleteNotice(chatRoomId, noticeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // 공지 목록 조회
    @GetMapping
    public ResponseEntity<List<NoticeResponseDTO>> getAllNotices(@PathVariable Long chatRoomId) {
        List<NoticeResponseDTO> notices = noticeService.getAllNotices(chatRoomId);
        return new ResponseEntity<>(notices, HttpStatus.OK);
    }

    // 공지 상세 조회
    @GetMapping("/{noticeId}")
    public ResponseEntity<NoticeResponseDTO> getNoticeDetail(@PathVariable Long chatRoomId, @PathVariable Long noticeId) {
        NoticeResponseDTO notice = noticeService.getNoticeDetail(chatRoomId, noticeId);
        return new ResponseEntity<>(notice, HttpStatus.OK);
    }
}
