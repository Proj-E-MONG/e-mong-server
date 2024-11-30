package com.yours.emong.domain.schedule.dto;

import com.yours.emong.domain.schedule.entity.DateSelectionEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class DateSelectionDTO {

    private Long userId;
    private LocalDate selectedDate;

    public static DateSelectionDTO toDateSelectionDTO(DateSelectionEntity entity) {
        return new DateSelectionDTO(
                entity.getUser().getId(),
                entity.getSelectedDate());
    }

}
