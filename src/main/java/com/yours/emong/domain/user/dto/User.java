package com.yours.emong.domain.user.dto;

import com.yours.emong.domain.school.domain.SchoolEntity;
import com.yours.emong.domain.user.domain.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@Component
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String serialNumber;
    private String name;
    private SchoolEntity school;
    private LocalDate accessStartDate;
    private LocalDate accessEndDate;
    private String phoneNumber;

    public static User toUser(UserEntity userEntity) {
        return User.builder()
                .serialNumber(userEntity.getSerialNumber())
                .name(userEntity.getName())
                .school(userEntity.getSchool())
                .accessStartDate(userEntity.getAccessStartDate())
                .accessEndDate(userEntity.getAccessEndDate())
                .build();

    }
}
