package com.yours.emong.domain.schedule.controller;

import com.yours.emong.domain.schedule.dto.DateSelectionDTO;
import com.yours.emong.domain.schedule.dto.request.DateSelectionRequest;
import com.yours.emong.domain.schedule.dto.response.DetailSelectionResponse;
import com.yours.emong.domain.schedule.dto.response.ScheduleResponse;
import com.yours.emong.domain.schedule.service.ScheduleService;
import com.yours.emong.global.common.response.BaseResponseData;
import com.yours.emong.global.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;


    // 일정 선택(유저 개인이 원하는 날짜를 확정)
    @PostMapping("/select-date/{scheduleId}")
    public BaseResponseData<ScheduleResponse> selectDate(
            @PathVariable Long scheduleId,
            @RequestBody DateSelectionRequest dateSelectionRequest) {

        ScheduleResponse scheduleResponse = scheduleService.selectDate(dateSelectionRequest.getUserId(), scheduleId, dateSelectionRequest.getSelectedDate());
        return BaseResponseData.ok("일정 선택이 완료되었습니다.", scheduleResponse);
    }

    // 일정 취소
    @PostMapping("cancel-date/{scheduleId}/{dateSelectionId}")
    public ResponseEntity<String> cancelDate(@PathVariable Long scheduleId, @PathVariable Long dateSelectionId) {
        scheduleService.cancelDate(scheduleId, dateSelectionId);
        return ResponseEntity.ok("일정 취소가 완료되었습니다.");
    }

    // 선택된 일정 모두 가져오기
    @GetMapping("/selections/{scheduleId}")
    public BaseResponseData<List<DateSelectionDTO>> getSelections(@PathVariable Long scheduleId) {
        List<DateSelectionDTO> selections = scheduleService.getAllSelections(scheduleId);
        return BaseResponseData.ok("선택된 모든 일정을 가져옵니다.", selections);
    }

    @GetMapping("/view/{scheduleId}/{dateSelectionId}") //날짜 속 정보 확인
    public BaseResponseData<DetailSelectionResponse> viewConfirmedSchedule(@PathVariable Long scheduleId, @PathVariable Long dateSelectionId) {
        return BaseResponseData.ok("자세한 일정 내용을 가져옵니다.", scheduleService.getDetailSelection(scheduleId, dateSelectionId));
    }
}



//    @PostMapping("/{scheduleId}/confirm")
//    public ResponseEntity<Void> confirmSchedule(@PathVariable Long scheduleId) {
//        scheduleService.confirmSchedule(scheduleId);
//        return ResponseEntity.ok().build();
//    }