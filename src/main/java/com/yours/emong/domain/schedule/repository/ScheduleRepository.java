package com.yours.emong.domain.schedule.repository;

import com.yours.emong.domain.schedule.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {

}
