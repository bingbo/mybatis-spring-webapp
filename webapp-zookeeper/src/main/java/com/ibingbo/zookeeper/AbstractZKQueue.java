package com.ibingbo.zookeeper;

import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by bing on 17/7/19.
 */
public abstract class AbstractZKQueue implements ZKQueue {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractZKQueue.class);

    protected ZooKeeper zooKeeper;
    protected String QUEUE_ROOT = "/QUEUE";

    public AbstractZKQueue() throws Exception {
        this.zooKeeper = ZooKeeperUtil.getInstance();
        ZooKeeperUtil.createPathIfNotExists(this.zooKeeper, QUEUE_ROOT);
    }

}
