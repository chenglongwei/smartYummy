package com.smartYummy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * Created by chenglongwei on 4/28/16.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class YummyException extends RuntimeException {
    public YummyException(String msg) {
        super(msg);
    }
}
