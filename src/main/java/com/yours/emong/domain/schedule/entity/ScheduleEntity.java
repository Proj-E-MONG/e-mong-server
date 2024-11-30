package com.yours.emong.domain.schedule.entity;

import com.yours.emong.domain.user.domain.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "start_date", nullable = false)
    private LocalDate endDate;


    @Column(name = "final_selected_date", nullable = true)
    private LocalDate finalSelectedDate; //최종으로 실제로 잡힌 날짜.

    @Column(nullable = false)
    private boolean isActive; //스케쥴 확정 났는가.

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<DateSelectionEntity> dateSelectionEntities = new ArrayList<>(); //여러 유저들이 선택한 모든 날짜들.
}