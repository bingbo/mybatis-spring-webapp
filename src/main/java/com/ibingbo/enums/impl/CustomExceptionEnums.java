package com.ibingbo.enums.impl;


import com.ibingbo.enums.ExceptionEnums;

/**
 * Created by bing on 2016/12/27.
 */
public enum CustomExceptionEnums implements ExceptionEnums{


    MISSID(1000, "miss id"),

    NOTNULLOREMPTY(1001,"not null or empty");


    private int code;
    private String message;

    CustomExceptionEnums(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
