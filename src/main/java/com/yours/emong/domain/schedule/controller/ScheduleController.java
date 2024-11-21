package com.yours.emong.domain.schedule.controller;

import com.yours.emong.domain.schedule.dto.DateSelectionDTO;
import com.yours.emong.domain.schedule.dto.DateSelectionRequest;
import com.yours.emong.domain.schedule.dto.ScheduleDTO;
import com.yours.emong.domain.schedule.entity.ScheduleEntity;
import com.yours.emong.domain.schedule.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleDTO> createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        ScheduleDTO createdSchedule = scheduleService.createSchedule(scheduleDTO);
        return ResponseEntity.ok(createdSchedule);
    }


    @PostMapping("/{scheduleId}/select-date")
    public ResponseEntity<Void> selectDate(
            @PathVariable Long scheduleId,
            @RequestBody DateSelectionRequest dateSelectionRequest) {
        scheduleService.selectDate(dateSelectionRequest.getUserId(), scheduleId, dateSelectionRequest.getSelectedDate());
        return ResponseEntity.ok().build();
    }


    @GetMapping("/{scheduleId}/selections")
    public ResponseEntity<List<DateSelectionDTO>> getSelections(@PathVariable Long scheduleId) {
        List<DateSelectionDTO> selections = scheduleService.getSelectionsForSchedule(scheduleId);
        return ResponseEntity.ok(selections);
    }

    @PostMapping("/{scheduleId}/confirm")
    public ResponseEntity<Void> confirmSchedule(@PathVariable Long scheduleId) {
        scheduleService.confirmSchedule(scheduleId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{scheduleId}/view")
    public ResponseEntity<ScheduleEntity> viewConfirmedSchedule(@PathVariable Long scheduleId) {
        ScheduleEntity schedule = scheduleService.getConfirmedSchedule(scheduleId);
        if (schedule.isConfirmed()) {
            return ResponseEntity.ok(schedule);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}