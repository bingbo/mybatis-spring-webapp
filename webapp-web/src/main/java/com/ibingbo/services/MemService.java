package com.ibingbo.services;

import net.rubyeye.xmemcached.exception.MemcachedException;

import java.util.concurrent.TimeoutException;

/**
 * Created by bing on 2017/1/12.
 */
public interface MemService {

    String get(String key) throws InterruptedException, MemcachedException, TimeoutException;

    void set(String key, String value) throws InterruptedException, MemcachedException, TimeoutException;
}
