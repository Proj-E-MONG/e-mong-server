package com.yours.emong.domain.schedule.service;

import com.yours.emong.domain.schedule.entity.DateSelectionEntity;
import com.yours.emong.domain.schedule.entity.ScheduleEntity;
import com.yours.emong.domain.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CleanUpScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Scheduled(cron =  "0 0 0 * * ?")
    public void cleanUpExpiredSchedule() {
        LocalDate now = LocalDate.now();

        List<ScheduleEntity> schedules = scheduleRepository.findAll();
        for(ScheduleEntity schedule : schedules) {
            if(schedule.getStartDate().plusDays(7).isBefore(now) && schedule.isActive()){
                schedule.setActive(false);
                scheduleRepository.save(schedule);

                LocalDate mostSelectedDate = findMostSelectedDate(schedule);

                if (mostSelectedDate != null) {
                    schedule.setFinalSelectedDate(mostSelectedDate);
                }
                schedule.setActive(false);
                scheduleRepository.save(schedule);
            }
        }
    }

    // 가장 많이 선택된 날짜 찾기. 복수일 경우 가장 빠른 일정으로.
    public LocalDate findMostSelectedDate(ScheduleEntity schedule) {
        Map<LocalDate, Long> dateCountMap = schedule.getDateSelectionEntities()
                .stream()
                .collect(Collectors.groupingBy(
                        DateSelectionEntity::getSelectedDate,
                        Collectors.counting() //선택된 각각의 날짜와 해당 선택 횟수를 map으로 저장.
                ));

        Long maxCount = dateCountMap.values().stream()
                .max(Comparator.naturalOrder()) //오름차순 정렬
                .orElse(0L);

        return dateCountMap.entrySet().stream()
                .filter(entry -> entry.getValue().equals(maxCount)) //수를 비교할 땐 .equals()
                .map(Map.Entry::getKey) //key(LocalDate) 만을 반환
                .min(Comparator.naturalOrder()) //오름차순 된것중 가장 앞에거(최근) 반환
                .orElse(null);
    }
}
