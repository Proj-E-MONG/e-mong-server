package com.yours.emong.domain.notice.service;


import com.yours.emong.domain.chat.domain.room.ChatRoomEntity;
import com.yours.emong.domain.chat.domain.room.repository.ChatRoomRepository;
import com.yours.emong.domain.notice.dto.NoticeCreateRequestDTO;
import com.yours.emong.domain.notice.dto.NoticeUpdateRequestDTO;
import com.yours.emong.domain.notice.entity.NoticeEntity;
import com.yours.emong.domain.notice.repository.NoticeRepository;
import com.yours.emong.domain.user.domain.UserEntity;
import com.yours.emong.domain.user.domain.repository.jpa.UserJpaRepository;
import com.yours.emong.global.security.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final UserJpaRepository userJpaRepository;

    // 공지 생성 로직
    public NoticeEntity createNotice(NoticeCreateRequestDTO requestDto, Long chatRoomId) {
        UserEntity author = fetchUserFromContext(); // 현재 사용자 가져오기
        ChatRoomEntity chatRoom = chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 채팅방입니다."));

        NoticeEntity notice = NoticeEntity.builder()
                .ntcTitle(requestDto.getNtcTitle())
                .ntcContent(requestDto.getNtcContent())
                .author(author)
                .chatRoom(chatRoom)
                .createdAt(LocalDateTime.now())
                .build();

        return noticeRepository.save(notice);
    }

    // 공지 수정 로직
    public NoticeEntity updateNotice(NoticeUpdateRequestDTO requestDto) {
        UserEntity requester = fetchUserFromContext(); // 현재 사용자 가져오기
        NoticeEntity notice = noticeRepository.findById(requestDto.getNoticeId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 공지입니다."));

        if (!notice.getAuthor().equals(requester)) {
            throw new IllegalArgumentException("공지 수정 권한이 없습니다.");
        }

        notice.setNtcTitle(requestDto.getTitle());
        notice.setNtcContent(requestDto.getContent());
        notice.setUpdatedAt(LocalDateTime.now()); // 수정 시간 갱신

        return noticeRepository.save(notice);
    }

    private UserEntity fetchUserFromContext() {
        // 현재 인증된 사용자 가져오기
        // SecurityContextHolder에서 인증된 사용자 정보 가져오기
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userSerial = userDetails.getUsername(); // userDetails 에서 시리얼 추출.

        // UserEntity를 DB에서 가져오기
        return userJpaRepository.findBySerialNumber(userSerial)
                .orElseThrow(() -> new IllegalArgumentException("현재 사용자를 찾을 수 없습니다."));
    }
}