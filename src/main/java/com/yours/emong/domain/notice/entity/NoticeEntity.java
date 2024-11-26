package com.yours.emong.domain.notice.entity;


import com.yours.emong.domain.Chat.ChatRoomEntity;
import com.yours.emong.domain.user.domain.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ntcId;

    @Column(nullable = false)
    private String ntcTitle;

    @Column(nullable = false, length = 10000)
    private String ntcContent;

    @Column(nullable = false)
    private LocalDateTime createdAt; // 작성일

    @Column(nullable = true)
    private LocalDateTime updatedAt; // 수정일

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private UserEntity author; // 작성자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id", nullable = false)
    private ChatRoomEntity chatRoom; // 소속 채팅방
}