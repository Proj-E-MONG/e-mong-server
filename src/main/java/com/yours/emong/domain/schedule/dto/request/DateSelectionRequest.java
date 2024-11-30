package com.yours.emong.domain.schedule.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DateSelectionRequest {

    @NotBlank
    private Long userId;
    @NotBlank
    private LocalDate selectedDate;

}