package com.yours.emong.domain.school.exception;

import com.yours.emong.domain.school.domain.SchoolEntity;
import com.yours.emong.domain.school.exception.error.SchoolError;
import com.yours.emong.domain.user.exception.UserNotFoundException;
import com.yours.emong.domain.user.exception.error.UserError;
import com.yours.emong.global.error.BusinessException;

public class SchoolNotFoundException extends BusinessException {

    public static final SchoolNotFoundException EXCEPTION = new SchoolNotFoundException();

    public SchoolNotFoundException() {
        super(SchoolError.SCHOOL_NOT_FOUND);
    }
}
