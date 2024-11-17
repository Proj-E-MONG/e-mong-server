package com.yours.emong.domain.user.exception;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.yours.emong.domain.user.exception.error.UserError;
import com.yours.emong.global.error.BusinessException;

public class UserInfoMismatchedException extends BusinessException {

  public static final UserInfoMismatchedException EXCEPTION = new UserInfoMismatchedException();

  public UserInfoMismatchedException() {
        super(UserError.USER_INFO_MISMATCHED);
    }
}
