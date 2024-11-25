package com.yours.emong.domain.schedule.repository;

import com.yours.emong.domain.schedule.entity.DateSelectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DateSelectionRepository extends JpaRepository<DateSelectionEntity, Long> {
    List<DateSelectionEntity> findByScheduleId(Long scheduleId);
}
