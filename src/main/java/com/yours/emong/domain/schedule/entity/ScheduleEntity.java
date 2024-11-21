package com.yours.emong.domain.schedule.entity;

import com.yours.emong.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate startDate;

    private LocalDate endDate;

    private String title;

    private boolean isConfirmed = false;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<DateSelectionEntity> dateSelectionEntities = new ArrayList<>();

    public void toggleDateSelection(User user, LocalDate date) {
        if (isConfirmed) {
            throw new IllegalStateException("이미 확정된 일정은 수정할 수 없습니다.");
        }

        Optional<DateSelectionEntity> existingSelection = dateSelectionEntities.stream()
                .filter(selection -> selection.getUser().equals(user) && selection.getSelectedDate().equals(date))
                .findFirst();

        if (existingSelection.isPresent()) {
            dateSelectionEntities.remove(existingSelection.get());
        } else {
            DateSelectionEntity newSelection = new DateSelectionEntity(user, date, this);
            dateSelectionEntities.add(newSelection);
        }
    }

    public void confirmSchedule() {
        this.isConfirmed = true;
    }
}