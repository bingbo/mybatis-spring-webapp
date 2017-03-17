package com.ibingbo.models;

import java.io.Serializable;

/**
 * Created by bing on 2017/1/12.
 */
public class Response<T> implements Serializable{

    private Integer code = 0;
    private String message = "ok";
    private T data;

    public Response() {
    }

    public Response(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
