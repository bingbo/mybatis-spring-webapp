package com.ibingbo.exception;

import com.ibingbo.enums.ExceptionEnums;
import com.ibingbo.enums.impl.CustomExceptionEnums;

/**
 * Created by bing on 2016/12/27.
 */
public class CustomException extends Exception{

    private ExceptionEnums exceptionEnums;
    private int code;
    private String message;


    public CustomException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
    public CustomException(ExceptionEnums exceptionEnums) {
        this(exceptionEnums.getCode(), exceptionEnums.getMessage());
        this.exceptionEnums = exceptionEnums;
    }

    @Override
    public String getMessage() {
        return this.exceptionEnums.getMessage();
    }

    public int getCode() {
        return this.exceptionEnums.getCode();
    }
}
