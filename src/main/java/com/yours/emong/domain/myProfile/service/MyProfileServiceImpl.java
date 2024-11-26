package com.yours.emong.domain.myProfile.service;

import com.yours.emong.domain.myProfile.domain.MyProfileEntity;
import com.yours.emong.domain.myProfile.domain.repository.jpa.MyProfileRepository;
import com.yours.emong.domain.myProfile.dto.MyProfile;
import com.yours.emong.domain.myProfile.dto.request.EditMyProfileRequest;
import com.yours.emong.domain.myProfile.exception.MyProfileNotFound;
import com.yours.emong.domain.user.domain.UserEntity;
import com.yours.emong.domain.user.domain.repository.jpa.UserJpaRepository;
import com.yours.emong.domain.user.dto.User;
import com.yours.emong.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyProfileServiceImpl implements MyProfileService{

    private final MyProfileRepository myProfileRepository;
    private final UserJpaRepository userJpaRepository;

    @Override
    public MyProfile getAllInformation(String serialNumber) {

        MyProfileEntity profileEntity = myProfileRepository.findByUserId(Long.parseLong(serialNumber))
                .orElseThrow(() -> MyProfileNotFound.EXCEPTION);
        //authentication 에서 넘어오는 userId 라는것은... 대체 뭐지 유저 정보를 저장해둔것이니 말그대로 유저의 id 인가.
        UserEntity findUser = userJpaRepository.findById(Long.parseLong(serialNumber))
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
        User userInstance = new User();

        return MyProfile.builder()
                .user(userInstance.toUser(findUser)) //User::toUser(findUser) 이렇게 바로 사용할 수 없는 이유는 뭐지
                .profileImageUrl(profileEntity.getProfileImageUrl())
                .introduction(profileEntity.getIntroduction())
                .username(findUser.getName()) //이름은 항상 고정
                .phoneNumber(profileEntity.getPhoneNumber())
                .mbti(profileEntity.getMbti())
                .relatedLinks(profileEntity.getRelatedLinks())
                .build();
    }

    @Override
    public void editMyProfile(EditMyProfileRequest request, String serialNumber) {

        MyProfileEntity myProfileEntity = myProfileRepository.findByUserId(Long.parseLong(serialNumber))
                .orElseThrow(() -> MyProfileNotFound.EXCEPTION);


        myProfileEntity.setProfileImageUrl(request.getProfileImageUrl());
        myProfileEntity.setIntroduction(request.getIntroduction());
        myProfileEntity.setPhoneNumber(request.getPhoneNumber());
        myProfileEntity.setRegion(request.getRegion());
        myProfileEntity.setThereLover(request.isThereLover());
        myProfileEntity.setMbti(request.getMbti());
        myProfileEntity.setRelatedLinks(request.getRelatedLinks());

        myProfileRepository.save(myProfileEntity);
    }
}
