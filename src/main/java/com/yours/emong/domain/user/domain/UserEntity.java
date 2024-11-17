package com.yours.emong.domain.user.domain;

import com.yours.emong.domain.school.domain.SchoolEntity;
import com.yours.emong.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;


@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_user")
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name="serial_number", nullable = false, unique = true)
    private String serialNumber;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "school_id", referencedColumnName = "id", nullable = false)
    private SchoolEntity school;

    @Column(name = "access_start_date", nullable = false)
    private LocalDate accessStartDate;

    @Column(name = "access_end_date", nullable = false)
    private LocalDate accessEndDate;
}
