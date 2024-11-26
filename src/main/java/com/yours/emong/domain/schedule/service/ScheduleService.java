package com.yours.emong.domain.schedule.service;

import com.yours.emong.domain.schedule.dto.DateSelectionDTO;
import com.yours.emong.domain.schedule.dto.ScheduleDTO;
import com.yours.emong.domain.schedule.entity.DateSelectionEntity;
import com.yours.emong.domain.schedule.entity.ScheduleEntity;
import com.yours.emong.domain.schedule.repository.DateSelectionRepository;
import com.yours.emong.domain.schedule.repository.ScheduleRepository;
import com.yours.emong.domain.user.domain.UserEntity;
import com.yours.emong.domain.user.domain.repository.jpa.UserJpaRepository;
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
    private final UserJpaRepository userRepository;

    public ScheduleDTO createSchedule(ScheduleDTO scheduleDTO) {
        ScheduleEntity schedule = new ScheduleEntity();
        schedule.setStartDate(scheduleDTO.getStartDate());
        schedule.setEndDate(scheduleDTO.getEndDate());
        schedule.setTitle(scheduleDTO.getTitle());

        schedule = scheduleRepository.save(schedule);
        return convertToDTO(schedule);
    }

    public void selectDate(Long userId, Long scheduleId, LocalDate selectedDate) {
        ScheduleEntity schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid schedule ID"));

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));

        schedule.toggleDateSelection(user, selectedDate);
        scheduleRepository.save(schedule);
    }

    public List<DateSelectionDTO> getSelectionsForSchedule(Long scheduleId) {
        List<DateSelectionEntity> selections = dateSelectionRepository.findByScheduleId(scheduleId);
        return selections.stream().map(this::convertToDTO).toList();
    }

    private ScheduleDTO convertToDTO(ScheduleEntity schedule) {
        ScheduleDTO dto = new ScheduleDTO();
        dto.setId(schedule.getId());
        dto.setStartDate(schedule.getStartDate());
        dto.setEndDate(schedule.getEndDate());
        dto.setTitle(schedule.getTitle());

        List<DateSelectionDTO> dateSelectionDTOs = schedule.getDateSelectionEntities()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        dto.setDateSelections(dateSelectionDTOs);

        return dto;
    }

    private DateSelectionDTO convertToDTO(DateSelectionEntity dateSelection) {
        DateSelectionDTO dto = new DateSelectionDTO();
        dto.setUserId(dateSelection.getUser().getId());
        dto.setSelectedDate(dateSelection.getSelectedDate());
        return dto;
    }

    public void confirmSchedule(Long scheduleId) {
        ScheduleEntity schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("일정을 찾을 수 없습니다."));

        schedule.confirmSchedule();
        scheduleRepository.save(schedule);
    }

    public ScheduleEntity getConfirmedSchedule(Long scheduleId) {
        return scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("일정을 찾을 수 없습니다."));
    }
}
