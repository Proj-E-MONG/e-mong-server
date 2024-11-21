package com.yours.emong.global.security.jwt.exception;

import com.yours.emong.global.error.BusinessException;
import com.yours.emong.global.security.jwt.exception.error.JwtTokenError;

public class TokenTypeException extends BusinessException {

  public static final TokenTypeException EXCEPTION = new TokenTypeException();

  private TokenTypeException() { super(JwtTokenError.JWT_TOKEN_ERROR);}
}

//"잘못된 타입"