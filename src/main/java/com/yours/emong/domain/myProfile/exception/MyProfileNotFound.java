package com.yours.emong.domain.myProfile.exception;

import com.yours.emong.domain.myProfile.exception.error.MyProfileError;
import com.yours.emong.global.error.BusinessException;

public class MyProfileNotFound extends BusinessException {

    public static final MyProfileNotFound EXCEPTION = new MyProfileNotFound();

    public MyProfileNotFound() {
        super(MyProfileError.MY_PROFILE_NOT_FOUND);
    }
}
