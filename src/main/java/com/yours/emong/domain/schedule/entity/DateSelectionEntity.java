package com.yours.emong.domain.schedule.entity;


import com.yours.emong.domain.user.User;
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
    private User user;

    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    private ScheduleEntity scheduleEntity;

    private LocalDate selectedDate;

    public DateSelectionEntity(User user, LocalDate selectedDate, ScheduleEntity scheduleEntity) {
        this.user = user;
        this.selectedDate = selectedDate;
        this.scheduleEntity = scheduleEntity;
    }

}
