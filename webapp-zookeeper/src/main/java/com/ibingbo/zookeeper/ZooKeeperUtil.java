package com.ibingbo.zookeeper;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by bing on 17/7/19.
 */
public class ZooKeeperUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ZooKeeperUtil.class);

    private static String CONNECTION_STRING = "localhost:2181";
    private static int SESSION_TIMEOUT = 10000;

    public static ZooKeeper getInstance() throws Exception {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        ZooKeeper zooKeeper = new ZooKeeper(CONNECTION_STRING, SESSION_TIMEOUT, new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                    countDownLatch.countDown();
                }else {
                    LOGGER.info("zookeeper connected .... ");
                }
            }
        });
        countDownLatch.await(SESSION_TIMEOUT, TimeUnit.MILLISECONDS);
        return zooKeeper;
    }

    public static void init(String host, int timeout) {
        CONNECTION_STRING = host;
        SESSION_TIMEOUT = timeout;
    }

    public static void createPathIfNotExists(ZooKeeper zooKeeper, String path) {
        try {
            Stat stat = zooKeeper.exists(path, false);
            if (stat == null) {
                zooKeeper.create(path, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
