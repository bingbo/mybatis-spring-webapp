package com.ibingbo.services;

/**
 * Created by bing on 2017/1/13.
 */
public interface RedisService {

    boolean set(String key, String value);

    Object get(String key);
}
