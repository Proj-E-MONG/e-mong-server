package com.yours.emong.domain.myProfile.service;

import com.yours.emong.domain.myProfile.dto.MyProfile;
import com.yours.emong.domain.myProfile.dto.request.EditMyProfileRequest;

public interface MyProfileService {

    MyProfile getAllInformation(String serialNumber);

    void editMyProfile(EditMyProfileRequest request, String serialNumber);
}
