package com.ibingbo.services.impl;

import com.ibingbo.services.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * Created by bing on 2017/1/13.
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    @Qualifier("jedisPool")
    private ShardedJedisPool pool;


    @Override
    public boolean set(String key, String value) {
        String result = pool.getResource().set(key, value);
        return true;
    }

    @Override
    public Object get(String key) {
        return pool.getResource().get(key);
    }
}
