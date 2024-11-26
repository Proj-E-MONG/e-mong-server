package com.yours.emong.domain.myProfile.controller;

import com.yours.emong.domain.myProfile.dto.MyProfile;
import com.yours.emong.domain.myProfile.dto.request.EditMyProfileRequest;
import com.yours.emong.domain.myProfile.service.MyProfileService;
import com.yours.emong.global.common.response.BaseResponse;
import com.yours.emong.global.common.response.BaseResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/myProfile")
public class MyProfileController {

    private final MyProfileService myProfileService;
    //조회 생성 수정 삭제

    @GetMapping()
    public BaseResponseData<MyProfile> getProfile(Principal principal) {
        return BaseResponseData.ok(
                "유저 프로필 조회 완료", myProfileService.getAllInformation(principal.getName()));

    }

    @PatchMapping()
    public BaseResponse editProfile(EditMyProfileRequest request, Principal principal) {
        myProfileService.editMyProfile(request, principal.getName());
        return BaseResponse.ok("유저 프로필 수정 완료");
    }
}
