package com.yours.emong.domain.schedule.entity;


import com.yours.emong.domain.user.domain.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class DateSelectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    private ScheduleEntity schedule;  // 필드명을 schedule로 변경 (더 직관적)

    private LocalDate selectedDate;

    public DateSelectionEntity(UserEntity user, LocalDate selectedDate, ScheduleEntity schedule) {
        this.user = user;
        this.selectedDate = selectedDate;
        this.schedule = schedule; // scheduleEntity -> schedule로 변경
    }

}