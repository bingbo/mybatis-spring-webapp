package com.ibingbo.services;

/**
 * Created by bing on 2017/1/12.
 */
public interface MemService {

    String get(String key);

    void set(String key, String value);
}
