package com.ibingbo.common;

import org.springframework.util.StringUtils;
import redis.clients.jedis.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bing on 2017/1/13.
 */
public class Redis {


    //简单应用
    public static Jedis getInstance() {
        Jedis redis = new Jedis("127.0.0.1");
        return redis;
    }

    public static Jedis getInstance(String host, int port) {
        return new Jedis(host, port);
    }

    public static Jedis getInstance(String host, int port, String password) {
        Jedis redis = new Jedis(host, port);
        if (!StringUtils.isEmpty(password)) {
            redis.auth(password);
        }
        return redis;
    }

    //通过根据配置建立Jedis连接池，获取连接
    public static Jedis getInstance(JedisPoolConfig config, String host, int port) {
        JedisPool pool = null;
        if (null == config) {
            //建立连接池配置参数
            config = new JedisPoolConfig();
            //设置最大连接数
            config.setMaxTotal(100);
            //设置最大空闲连接数
            config.setMaxIdle(10);
            //设置最大阻塞时间，单位许毫秒
            config.setMaxWaitMillis(1000);
        }
        //同步创建连接池
        synchronized (pool) {
            if (null == pool) {
                pool = new JedisPool(config, host, port);
            }
        }
        return pool.getResource();
    }

    //Jedis 实现分片,Jedis分片采用Hash算法和基于的Key模式匹配。Jedis定义一个Hash接口，如果觉得自带的不爽，可以自己实现一个Hash算法。Jedis自带的Hash的算法是MurmurHash 2.0 。
    public static ShardedJedis getInstance(List<JedisShardInfo> shards) {
        if (null == shards) {
            //建立服务器列表
            shards = new ArrayList<JedisShardInfo>();

            //添加第一台服务器信息
            JedisShardInfo si = new JedisShardInfo("localhost", 6379);
            si.setPassword("123");
            shards.add(si);

            //添加第二台服务器信息
            si = new JedisShardInfo("localhost", 6399);
            si.setPassword("123");
            shards.add(si);
        }
        //建立分片连接对象
        ShardedJedis jedis = new ShardedJedis(shards);

        //建立分片连接对象,并指定Hash算法
        //ShardedJedis jedis = new ShardedJedis(shards,selfHash);
        return jedis;
    }

    //分片也可以支持连接池
    public static ShardedJedis getInstance(JedisPoolConfig config, List<JedisShardInfo> shards) {
        ShardedJedisPool pool = new ShardedJedisPool(config, shards);
        return pool.getResource();
    }

}
