package com.yours.emong.domain.school.domain.repository.jpa;

import com.yours.emong.domain.school.domain.SchoolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.Year;
import java.util.Optional;

public interface SchoolJpaRepository extends JpaRepository<SchoolEntity, Long> {
    @Query(value = "SELECT * FROM tb_school WHERE school_name = :schoolName AND YEAR(graduation_date) = :graduationYear", nativeQuery = true)
    SchoolEntity getBySchoolNameAndGraduationYear(@Param("schoolName") String schoolName,
                                                   @Param("graduationYear") int graduationYear);
}
