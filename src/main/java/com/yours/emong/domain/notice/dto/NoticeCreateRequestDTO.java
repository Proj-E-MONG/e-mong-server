package com.yours.emong.domain.notice.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NoticeCreateRequestDTO {

    @NotBlank
    private String ntcTitle;

    @NotBlank
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