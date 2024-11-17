package com.yours.emong.domain.user.domain.repository.jpa;

import com.yours.emong.domain.user.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findBySerialNumber(String serialNumber);

    @Query(value = "SELECT * FROM tb_user WHERE phone_number = :phoneNumber AND (access_start_date IS NULL OR Year(access_start_date) = :graduationYear)", nativeQuery = true)
    Optional<UserEntity> getByPhoneNumberAndAccessStartDate(@Param("phoneNumber") String phoneNumber,
                                                         @Param("graduationYear") int graduationYear);
    @Query(value = "SELECT u FROM UserEntity u JOIN u.school s WHERE u.serialNumber = :serialNumber AND u.name = :name AND s.schoolName = :schoolName")
    Optional<UserEntity> findBySerialNumberAndNameAndSchoolName(@Param("serialNumber") String serialNumber,
                                                                @Param("name") String name,
                                                                @Param("schoolName") String schoolName);
}
