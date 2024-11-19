package com.yours.emong.domain.schedule.service;

import com.yours.emong.domain.schedule.dto.DateSelectionDTO;
import com.yours.emong.domain.schedule.dto.ScheduleDTO;
import com.yours.emong.domain.schedule.entity.DateSelection;
import com.yours.emong.domain.schedule.entity.Schedule;
import com.yours.emong.domain.schedule.repository.DateSelectionRepository;
import com.yours.emong.domain.schedule.repository.ScheduleRepository;
import com.yours.emong.domain.user.User;
import com.yours.emong.domain.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final DateSelectionRepository dateSelectionRepository;
    private final UserRepository userRepository;

    public ScheduleDTO createSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        schedule.setStartDate(scheduleDTO.getStartDate());
        schedule.setEndDate(scheduleDTO.getEndDate());
        schedule.setTitle(scheduleDTO.getTitle());

        schedule = scheduleRepository.save(schedule);
        return convertToDTO(schedule);
    }

    public void selectDate(Long userId, Long scheduleId, LocalDate selectedDate) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid schedule ID"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));

        schedule.toggleDateSelection(user, selectedDate);
        scheduleRepository.save(schedule);
    }

    public List<DateSelectionDTO> getSelectionsForSchedule(Long scheduleId) {
        List<DateSelection> selections = dateSelectionRepository.findByScheduleId(scheduleId);
        return selections.stream().map(this::convertToDTO).toList();
    }

    private ScheduleDTO convertToDTO(Schedule schedule) {
        ScheduleDTO dto = new ScheduleDTO();
        dto.setId(schedule.getId());
        dto.setStartDate(schedule.getStartDate());
        dto.setEndDate(schedule.getEndDate());
        dto.setTitle(schedule.getTitle());

        List<DateSelectionDTO> dateSelectionDTOs = schedule.getDateSelections()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        dto.setDateSelections(dateSelectionDTOs);

        return dto;
    }

    private DateSelectionDTO convertToDTO(DateSelection dateSelection) {
        DateSelectionDTO dto = new DateSelectionDTO();
        dto.setUserId(dateSelection.getUser().getId());
        dto.setSelectedDate(dateSelection.getSelectedDate());
        return dto;
    }

    public void confirmSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("일정을 찾을 수 없습니다."));

        schedule.confirmSchedule();
        scheduleRepository.save(schedule);
    }

    public Schedule getConfirmedSchedule(Long scheduleId) {
        return scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("일정을 찾을 수 없습니다."));
    }
}
