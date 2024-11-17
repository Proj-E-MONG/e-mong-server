package com.yours.emong.domain.user.exception;

import com.yours.emong.domain.user.exception.error.UserError;
import com.yours.emong.global.error.BusinessException;

public class UserExistException extends BusinessException {

    public static final UserExistException EXCEPTION = new UserExistException();

    public UserExistException() {
        super(UserError.USER_EXIST);
    }
}
