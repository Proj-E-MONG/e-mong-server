package com.yours.emong.global.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BusinessException extends RuntimeException {

  private final ErrorProperty errorProperty;
}
//businessException을 상속받아 errorProperty의 공통된 에러 처리를 하도록 관리. (에러코드, 메시지)