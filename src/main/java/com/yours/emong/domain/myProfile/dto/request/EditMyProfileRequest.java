package com.yours.emong.domain.myProfile.dto.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EditMyProfileRequest {
    //이름은 변경 불가. 디비에 저장된 이름으로 사용.

    private String profileImageUrl;

    @Size(max = 50, message = "한 줄 소개는 50자로 제한됩니다.")
    private String introduction;

    private String region;

    @Pattern(regexp = "^\\d{4}\\.\\d{2}\\.\\d{2}$", message = "생년월일은 yyyy.mm.dd 형식이여야 합니다.")
    private String birth;

    private boolean hasLover;

    @Pattern(regexp = "^[A-Z]{4}$", message = "MBTI는 영어 대문자 4자리여야 합니다.")
    private String mbti;

    @Size(max = 3, message = "관련 링크는 3개까지만 포함시킬 수 있습니다.")
    private List<String> relatedLinks;
}
