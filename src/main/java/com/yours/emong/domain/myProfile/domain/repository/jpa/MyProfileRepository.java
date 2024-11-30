package com.yours.emong.domain.myProfile.domain.repository.jpa;

import com.yours.emong.domain.myProfile.domain.MyProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyProfileRepository extends JpaRepository<MyProfileEntity, Long> {

    Optional<MyProfileEntity> findBySerialNumber(String serialNumber);

}
