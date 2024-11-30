package com.yours.emong.domain.myProfile.dto.response;

import com.yours.emong.domain.myProfile.domain.MyProfileEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class EditMyProfileResponse {

    private String profileImageUrl;

    private String introduction;

    private String username;

    private String phoneNumber;

    private String region;

    private String birth;

    private boolean hasLover;

    private String mbti;

    private List<String> relatedLinks;

    public static EditMyProfileResponse toEditMyProfileResponse(MyProfileEntity entity) {
        return new EditMyProfileResponse(
                entity.getProfileImageUrl(),
                entity.getIntroduction(),
                entity.getUsername(),
                entity.getPhoneNumber(),
                entity.getRegion(),
                entity.getBirth(),
                entity.isHasLover(),
                entity.getMbti(),
                entity.getRelatedLinks()
        );
    }
}
