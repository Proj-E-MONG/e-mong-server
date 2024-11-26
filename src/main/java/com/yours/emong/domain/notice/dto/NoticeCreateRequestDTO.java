package com.yours.emong.domain.notice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NoticeCreateRequestDTO {
    private Long chatRoomId;
    private String ntcTitle;
    private String ntcContent;
}




//public class NoticeResponseDto {
//    private Long id;
//    private String title;
//    private String content;
//    private String authorName;
//    private LocalDateTime createdAt;
//    private LocalDateTime updatedAt;
//
//    public NoticeResponseDto(NoticeEntity notice) {
//        this.id = notice.getId();
//        this.title = notice.getTitle();
//        this.content = notice.getContent();
//        this.authorName = notice.getAuthor().getName();
//        this.createdAt = notice.getCreatedAt();
//        this.updatedAt = notice.getUpdatedAt();
//    }
//}