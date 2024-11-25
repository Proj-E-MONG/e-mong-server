package com.yours.emong.domain.schedule.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DateSelectionRequest {

    private Long userId;
    private LocalDate selectedDate;

}