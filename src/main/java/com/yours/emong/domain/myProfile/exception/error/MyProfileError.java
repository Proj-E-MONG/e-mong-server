package com.yours.emong.domain.myProfile.exception.error;

import com.yours.emong.global.error.ErrorProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MyProfileError implements ErrorProperty {

        MY_PROFILE_NOT_FOUND(HttpStatus.NOT_FOUND, "프로필을 찾을 수 없습니다.");

        private final HttpStatus status;
        private final String message;
}


