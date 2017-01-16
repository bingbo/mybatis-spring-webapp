package com.ibingbo.common;

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

/**
 * Created by bing on 2017/1/13.
 */
public class Memcache {

    private static MemCachedClient client = null;

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
        client.setCompressEnable(true);
        client.setSanitizeKeys(true);

    }

    private static Memcache memcache = new Memcache();
    private Memcache() {
    }

    public static Memcache getInstance() {
        return memcache;
    }

    public boolean set(String key, String value) {
        return memcache.set(key, value);
    }

    public Object get(String key) {
        return memcache.get(key);
    }

    public boolean delete(String key) {
        return memcache.delete(key);
    }
}
