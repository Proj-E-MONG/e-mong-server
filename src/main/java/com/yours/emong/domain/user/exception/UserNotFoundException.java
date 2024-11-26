package com.yours.emong.domain.user.exception;

import com.yours.emong.domain.user.exception.error.UserError;
import com.yours.emong.global.error.BusinessException;

public class UserNotFoundException extends BusinessException {

    public static final UserNotFoundException EXCEPTION = new UserNotFoundException();

    public UserNotFoundException() {
        super(UserError.USER_NOT_FOUND);
    }
}
