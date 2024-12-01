package com.yours.emong.domain.notice.repository;

import com.yours.emong.domain.notice.entity.NoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoticeRepository extends JpaRepository<NoticeEntity, Long> {
    // 특정 채팅방의 공지 목록 조회
    List<NoticeEntity> findByChatRoomId(Long chatRoomId);

    // 특정 채팅방의 특정 공지 조회
    Optional<NoticeEntity> findByNtcIdAndChatRoomId(Long noticeId, Long chatRoomId);  // 'ntcId'로 수정
}
