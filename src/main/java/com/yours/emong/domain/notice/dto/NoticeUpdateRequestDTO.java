package com.yours.emong.domain.notice.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NoticeUpdateRequestDTO {
    private Long ntcId;
    private String ntcTitle;
    private String ntcContent;
}
