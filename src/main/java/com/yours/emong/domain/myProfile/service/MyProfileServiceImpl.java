package com.yours.emong.domain.myProfile.service;

import com.yours.emong.domain.myProfile.domain.MyProfileEntity;
import com.yours.emong.domain.myProfile.domain.repository.jpa.MyProfileRepository;
import com.yours.emong.domain.myProfile.dto.MyProfile;
import com.yours.emong.domain.myProfile.dto.request.EditMyProfileRequest;
import com.yours.emong.domain.myProfile.dto.response.EditMyProfileResponse;
import com.yours.emong.domain.myProfile.dto.response.MyProfileResponse;
import com.yours.emong.domain.myProfile.exception.MyProfileNotFound;
import com.yours.emong.domain.user.domain.UserEntity;
import com.yours.emong.domain.user.domain.repository.jpa.UserJpaRepository;
import com.yours.emong.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyProfileServiceImpl implements MyProfileService{

    private final MyProfileRepository myProfileRepository;
    private final UserJpaRepository userJpaRepository;

    @Override
    public MyProfileResponse getAllInformation(String serialNumber) {

        MyProfileEntity profileEntity = myProfileRepository.findBySerialNumber(serialNumber)
                .orElseThrow(() -> MyProfileNotFound.EXCEPTION);

        return MyProfileResponse.toMyProfileResponse(profileEntity);
    }

    @Override
    public MyProfile createMyProfile(Long userId) { //회원가입 후 마이프로필 생성
        UserEntity user = userJpaRepository.findById(userId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        MyProfileEntity myProfileEntity = myProfileRepository.save(MyProfileEntity.builder()
                .serialNumber(user.getSerialNumber())
                .user(user)
                .username(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .build());

        return MyProfile.toMyProfile(myProfileEntity);
    }

    @Override
    @Transactional
    public EditMyProfileResponse editMyProfile(EditMyProfileRequest request, Principal principal) {
        log.info("애인 여부 요청 :  " + request.isHasLover());
        String serialNumber = principal.getName();

        MyProfileEntity myProfileEntity = myProfileRepository.findBySerialNumber(serialNumber)
                .orElseThrow(() -> MyProfileNotFound.EXCEPTION);

        myProfileEntity.setProfileImageUrl(request.getProfileImageUrl());
        myProfileEntity.setIntroduction(request.getIntroduction());
        myProfileEntity.setBirth(request.getBirth());
        myProfileEntity.setRegion(request.getRegion());

        System.out.println("istThereLover : " + request.isHasLover());
        myProfileEntity.setHasLover(request.isHasLover());
        myProfileEntity.setMbti(request.getMbti());
        myProfileEntity.setRelatedLinks(request.getRelatedLinks());

        MyProfileEntity savedMyProfile = myProfileRepository.save(myProfileEntity);

        return EditMyProfileResponse.toEditMyProfileResponse(savedMyProfile);
    }



}
