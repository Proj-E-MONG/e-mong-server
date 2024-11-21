package com.yours.emong.domain.school.exception.error;

import com.yours.emong.global.error.ErrorProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum SchoolError implements ErrorProperty {

    SCHOOL_NOT_FOUND(HttpStatus.NOT_FOUND, "학교를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String message;
}
