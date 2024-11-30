package com.yours.emong.domain.myProfile.service;

import com.yours.emong.domain.myProfile.dto.MyProfile;
import com.yours.emong.domain.myProfile.dto.request.EditMyProfileRequest;
import com.yours.emong.domain.myProfile.dto.response.EditMyProfileResponse;
import com.yours.emong.domain.myProfile.dto.response.MyProfileResponse;

import java.security.Principal;

public interface MyProfileService {

    MyProfileResponse getAllInformation(String serialNumber);
    MyProfile createMyProfile(Long userId);
    EditMyProfileResponse editMyProfile(EditMyProfileRequest request, Principal principal);
}
