package com.yours.emong.domain.user.exception.error;

import com.yours.emong.global.error.ErrorProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserError implements ErrorProperty {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다."),
    USER_FORBIDDEN(HttpStatus.FORBIDDEN, "권한이 없습니다."),
    SERIAL_NUMBER_WRONG(HttpStatus.BAD_REQUEST, "시리얼번호가 맞지 않습니다."),
    USER_EXIST(HttpStatus.CONFLICT, "이미 존재하는 유저입니다."),
    USER_INFO_MISMATCHED(HttpStatus.UNAUTHORIZED, "유저 인증에 실패했습니다.");

    private final HttpStatus status;
    private final String message;
}
