package com.yours.emong.domain.schedule.dto.response;

import com.yours.emong.domain.schedule.dto.DateSelectionDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class ScheduleResponse {

    private LocalDate startDate; //약속 지정 가능 시작 날짜
    private LocalDate endDate; //약속 지정 가능 끝 날짜
//    private boolean isConfirmed; //약속 확정 여부
    private List<DateSelectionDTO> dateSelections; //약속 날짜 -> 유저들이 선택한 모든 날짜
    private LocalDate selectedDate; //한 유저가 선택한 특정 날짜. (확정날짜 x)
    private String username; //어느 유저가 선택했는지 확인. -> 이름만


}
