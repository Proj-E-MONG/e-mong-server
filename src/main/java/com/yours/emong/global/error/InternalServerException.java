package com.yours.emong.global.error;

import com.yours.emong.domain.user.exception.error.UserError;

public class InternalServerException extends BusinessException {

    public InternalServerException() {
        super(UserError.SERIAL_NUMBER_WRONG);
    }
}
