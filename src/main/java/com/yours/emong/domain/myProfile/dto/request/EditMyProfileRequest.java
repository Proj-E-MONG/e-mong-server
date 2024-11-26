package com.yours.emong.domain.myProfile.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EditMyProfileRequest {
    //이름은 변경 불가. 디비에 저장된 이름으로 사용.

    private String profileImageUrl;

    private String introduction;

    private String phoneNumber;

    private String region;

    private boolean isThereLover;

    private String mbti;

    private List<String> relatedLinks;
}
