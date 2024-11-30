package com.yours.emong.domain.schedule.dto;

import com.yours.emong.domain.schedule.entity.DateSelectionEntity;
import com.yours.emong.domain.schedule.entity.ScheduleEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ScheduleDTO {
    private Long id;
    private LocalDate startDate; //약속 지정 가능 시작 날짜
    private LocalDate endDate; //약속 지정 가능 끝 날짜
    private boolean isActive; //약속 확정 여부
    private List<DateSelectionDTO> dateSelections; //약속 날짜
}


