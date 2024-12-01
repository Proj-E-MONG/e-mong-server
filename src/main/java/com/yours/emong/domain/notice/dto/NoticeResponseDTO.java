package com.yours.emong.domain.notice.dto;


import com.yours.emong.domain.notice.entity.NoticeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoticeResponseDTO {
    private Long ntcId;
    private String ntcTitle;
    private String ntcContent;
    private LocalDateTime createdAt;


    public static NoticeResponseDTO fromEntity(NoticeEntity entity) {
        return new NoticeResponseDTO(
                entity.getNtcId(),
                entity.getNtcTitle(),
                entity.getNtcContent(),
                entity.getCreatedAt()
        );
    }
}
