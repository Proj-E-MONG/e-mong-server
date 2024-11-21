package com.yours.emong.domain.user.exception;

import com.yours.emong.domain.user.exception.error.UserError;
import com.yours.emong.global.error.BusinessException;

public class SerialNumberWrongException extends BusinessException {

    public static final SerialNumberWrongException EXCEPTION = new SerialNumberWrongException(); //해당 예외를 사용하면 userError에 저장된 관련 메시지를 전달.

    private  SerialNumberWrongException() {
        super(UserError.SERIAL_NUMBER_WRONG);
    }
}
