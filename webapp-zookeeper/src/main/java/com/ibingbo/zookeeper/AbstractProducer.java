package com.ibingbo.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by bing on 17/7/19.
 */
public abstract class AbstractProducer extends AbstractZKQueue implements Producer {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractProducer.class);

    public AbstractProducer() throws Exception {
    }

    public String produce(String queue, byte[] bytes) throws Exception {
        String path = QUEUE_ROOT + "/" + queue;
        ZooKeeperUtil.createPathIfNotExists(this.zooKeeper, path);
        String node =
                this.zooKeeper.create(path + "/", bytes, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
        LOGGER.info("thread {} produce a message to queue: {} message data is: {}", Thread.currentThread().getName(),
                node, new String(bytes, "utf-8"));
        return node;
    }

    public boolean sendMessage(String queue, String data){
        try {
            byte[] bytes = data.getBytes();
            String node = this.produce(queue, bytes);
            return node != null;
        } catch (Exception e) {
            LOGGER.error("send message {} to {} fail", data, queue);
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }
}
