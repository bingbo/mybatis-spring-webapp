package com.ibingbo.services.impl;

import com.ibingbo.services.MemService;
import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;
import org.springframework.stereotype.Service;

/**
 * Created by bing on 2017/1/12.
 */
@Service
public class MemServiceImpl implements MemService{

    private MemCachedClient client;


    public MemServiceImpl() {
        SockIOPool pool = SockIOPool.getInstance();
        pool.setServers(new String[]{"127.0.0.1:11211"});
        pool.setFailover(true);
        pool.setInitConn(10);
        pool.setMinConn(5);
        pool.setMaxConn(20);
        pool.setMaintSleep(30);
        pool.setNagle(false);
        pool.setSocketTO(3000);
        pool.setAliveCheck(true);
        pool.initialize();
        client = new MemCachedClient();

    }

    @Override
    public String get(String key) {

        return client.get(key).toString();
    }

    @Override
    public void set(String key, String value) {
        client.set(key, value);
    }
}
