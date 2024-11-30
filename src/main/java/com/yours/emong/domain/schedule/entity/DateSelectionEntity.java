package com.yours.emong.domain.schedule.entity;


import com.yours.emong.domain.user.domain.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DateSelectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate selectedDate; //한 유저가 선택한 특정 날짜.

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user; //어느 유저가 선택했는지 확인.

    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    private ScheduleEntity schedule;  // 필드명을 schedule로 변경 (더 직관적)


}