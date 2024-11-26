package com.yours.emong.domain.schedule.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ScheduleDTO {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String title;
    private List<DateSelectionDTO> dateSelections;
}
