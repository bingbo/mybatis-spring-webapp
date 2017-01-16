package com.ibingbo.services.impl;

import com.ibingbo.services.MemService;
import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.MemcachedClientCallable;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.utils.AddrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeoutException;

/**
 * Created by bing on 2017/1/12.
 */
@Service
public class MemServiceImpl implements MemService{
    private static final Logger LOGGER = LoggerFactory.getLogger(MemServiceImpl.class);

    private static MemCachedClient client;
    @Autowired
    @Qualifier("xmemcachedClient1")
    private MemcachedClient xmemcachedClient;

    static  {
        SockIOPool pool = SockIOPool.getInstance();
        pool.setServers(new String[]{"127.0.0.1:11211","127.0.0.1:11212"});
        pool.setFailover(true);
        pool.setInitConn(10);
        pool.setMinConn(5);
        pool.setMaxConn(20);
        pool.setMaintSleep(30);
        pool.setNagle(false);
        pool.setSocketTO(3000);
        pool.setAliveCheck(true);
        pool.initialize();
        pool.setHashingAlg(1);
        client = new MemCachedClient();
        client.setDefaultEncoding("utf-8");
        client.setPrimitiveAsString(true);
        //google xmemcached
//        MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddressMap("localhost:11211"));
//        try {
//            this.gclient = builder.build();
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage(),e);
//        }

    }

    @Override
    public String get(String key) throws InterruptedException, MemcachedException, TimeoutException {

        //return client.get(key).toString();
        /*
        //在全名空间下设置值
        this.xmemcachedClient.withNamespace("namespace", new MemcachedClientCallable<Void>() {
            @Override
            public Void call(MemcachedClient memcachedClient) throws MemcachedException, InterruptedException, TimeoutException {
                memcachedClient.set("aa", 0, "aa");
                return null;
            }
        });
        //获取全名空间下的值
        this.xmemcachedClient.withNamespace("namespace", new MemcachedClientCallable<String>() {
            @Override
            public String call(MemcachedClient memcachedClient) throws MemcachedException, InterruptedException, TimeoutException {
                return memcachedClient.get("aaa");
            }
        });
        //使得命名空间失效
        this.xmemcachedClient.invalidateNamespace("namespace");
*       */
        return xmemcachedClient.get(key).toString();
    }

    @Override
    public void set(String key, String value) throws InterruptedException, MemcachedException, TimeoutException {
        //client.set(key, value);
        xmemcachedClient.set(key, 0, value);
    }
}
