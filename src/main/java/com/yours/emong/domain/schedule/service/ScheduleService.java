package com.yours.emong.domain.schedule.service;

import com.yours.emong.domain.schedule.dto.DateSelectionDTO;
import com.yours.emong.domain.schedule.dto.ScheduleDTO;
import com.yours.emong.domain.schedule.dto.response.DetailSelectionResponse;
import com.yours.emong.domain.schedule.dto.response.ScheduleResponse;
import com.yours.emong.domain.schedule.entity.DateSelectionEntity;
import com.yours.emong.domain.schedule.entity.ScheduleEntity;
import com.yours.emong.domain.schedule.repository.DateSelectionRepository;
import com.yours.emong.domain.schedule.repository.ScheduleRepository;
import com.yours.emong.domain.user.domain.UserEntity;
import com.yours.emong.domain.user.domain.repository.jpa.UserJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final DateSelectionRepository dateSelectionRepository;
    private final UserJpaRepository userRepository;

    public void createSchedule() { //스케쥴 시작 끝 날짜 초기화.
        LocalDate now = LocalDate.now();
        ScheduleEntity schedule = ScheduleEntity.builder()
                .startDate(now)
                .endDate(now.plusMonths(1))
                .isActive(true)
                .build();
        scheduleRepository.save(schedule);
    }

    public ScheduleResponse selectDate(Long userId, Long scheduleId, LocalDate selectedDate) {
        ScheduleEntity scheduleEntity = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("스케쥴이 존재하지 않습니다."));
        if(scheduleEntity.isActive()) {
            throw new IllegalArgumentException("선택 가능 기간이 종료되었습니다. (7일)");
        }
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));
        if(scheduleEntity.getFinalSelectedDate() != null)  {
            throw new IllegalArgumentException("확정된 일정으로 선택이 불가능합니다.");
        }
        if (selectedDate.isBefore(scheduleEntity.getStartDate()) || selectedDate.isAfter(scheduleEntity.getEndDate())) { //선택 마감 사이 일정만 선택 가능.
            throw new IllegalArgumentException("선택할 수 있는 날짜가 아닙니다.");
        }
        if (alreadySelected(scheduleEntity, userId, selectedDate)) {
            throw new IllegalArgumentException("이미 선택한 일정입니다.");
        }

        //한 유저가 선택한 일정.
        DateSelectionEntity dateSelectionEntity = DateSelectionEntity.builder()
                .selectedDate(selectedDate)
                .user(userEntity)
                .schedule(scheduleEntity)
                .build();
        List<DateSelectionEntity> dateSelectionEntities = scheduleEntity.getDateSelectionEntities();
        dateSelectionEntities.add(dateSelectionEntity); //종합 일정에 한 유저가 선택한 일정 추가.
        scheduleEntity.setDateSelectionEntities(dateSelectionEntities);

        scheduleRepository.save(scheduleEntity);


        List<DateSelectionDTO> dateSelectionDTOs = scheduleEntity.getDateSelectionEntities()
                .stream()
                .map(DateSelectionDTO::toDateSelectionDTO)
                .toList();
        return new ScheduleResponse(
                scheduleEntity.getStartDate(),
                scheduleEntity.getEndDate(),
                dateSelectionDTOs,
                selectedDate,
                userEntity.getName());
    }


    public void cancelDate(Long scheduleId, Long dateSelectionId) {
        ScheduleEntity scheduleEntity = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 스케쥴 입니다."));
        dateSelectionRepository.findById(dateSelectionId)
                .orElseThrow(() -> new IllegalArgumentException("선택된 일정이 없습니다."));

        List<DateSelectionEntity> dateSelectionEntities = scheduleEntity.getDateSelectionEntities();
        boolean isCanceled = dateSelectionEntities.removeIf(dateSelection -> dateSelection.getId().equals(dateSelectionId)); //duty check

        if(!isCanceled) {
            throw new IllegalArgumentException("삭제하려는 일정이 선택되어 있지 않습니다.");
        }

        scheduleEntity.setDateSelectionEntities(dateSelectionEntities);
        scheduleRepository.save(scheduleEntity);
    }

    private boolean alreadySelected(ScheduleEntity scheduleEntity, Long userId, LocalDate selectedDate) {
        return scheduleEntity.getDateSelectionEntities().stream()
                .anyMatch(selection -> selection.getUser().getId().equals(userId)
                         && selection.getSelectedDate().equals(selectedDate)); //같은 유저가 같은 일정 선택하지 못하도록.
    }

    public List<DateSelectionDTO> getAllSelections(Long scheduleId) {
        ScheduleEntity scheduleEntity = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 스케쥴 입니다."));

        return scheduleEntity.getDateSelectionEntities().stream()
                .map(DateSelectionDTO::toDateSelectionDTO)
                .toList();
    }

    public DetailSelectionResponse getDetailSelection(Long scheduleId, Long detailSelectionId) {
        scheduleRepository.findById(scheduleId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 스케쥴 입니다."));
        DateSelectionEntity dateSelectionEntity = dateSelectionRepository.findById(detailSelectionId)
                .orElseThrow(() -> new IllegalArgumentException("선택된 일정이 없습니다."));

        return new DetailSelectionResponse(dateSelectionEntity.getUser().getName());
    }


//
//
//    private DateSelectionDTO convertToDTO(DateSelectionEntity dateSelection) {
//        DateSelectionDTO dto = new DateSelectionDTO();
//        dto.setUserId(dateSelection.getUser().getId());
//        dto.setSelectedDate(dateSelection.getSelectedDate());
//        return dto;
//    }
//
//    public void confirmSchedule(Long scheduleId) {
//        ScheduleEntity schedule = scheduleRepository.findById(scheduleId)
//                .orElseThrow(() -> new IllegalArgumentException("일정을 찾을 수 없습니다."));
//
//        schedule.confirmSchedule();
//        scheduleRepository.save(schedule);
//    }
//
//    public ScheduleEntity getConfirmedSchedule(Long scheduleId) {
//        return scheduleRepository.findById(scheduleId)
//                .orElseThrow(() -> new IllegalArgumentException("일정을 찾을 수 없습니다."));
//    }
}
