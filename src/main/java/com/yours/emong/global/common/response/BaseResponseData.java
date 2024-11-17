package com.yours.emong.global.common.response;

import lombok.Getter;
import org.springframework.amqp.rabbit.listener.MessageAckListener;
import org.springframework.http.HttpStatus;

@Getter
public class BaseResponseData<T> extends BaseResponse{

    private final T data;

    private BaseResponseData(HttpStatus status, String message, T data) {
        super(status.value(), message);
        this.data = data;
    }

    public static <T> BaseResponseData <T> of (HttpStatus status, String message, T data) {
        return new BaseResponseData<>(status, message, data);
    } //들어온 data 타입의 형식으로 반환 됨.

    public static <T> BaseResponseData<T> ok (String message, T data) {
        return new BaseResponseData<>(HttpStatus.OK, message, data);
    }

    public static <T> BaseResponseData<T> created (String message, T data) {
        return new BaseResponseData<>(HttpStatus.CREATED, message, data);
    }
}
