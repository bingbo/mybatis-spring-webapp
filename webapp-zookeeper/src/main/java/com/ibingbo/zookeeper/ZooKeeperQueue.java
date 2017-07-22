package com.ibingbo.zookeeper;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

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
public class ZooKeeperQueue {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZooKeeperQueue.class);

    private ZooKeeper zk;
    private int sessionTimeout;

    private static final byte[] ROOT_QUEUE_DATA = {0x12, 0x34};
    private static String QUEUE_ROOT = "/QUEUE";
    private String queueName;
    private String queuePath;
    private Object mutex = new Object();

    public ZooKeeperQueue(String queueName) throws Exception {
        this.queueName = queueName;
        this.queuePath = QUEUE_ROOT + "/" + queueName;
        this.zk = ZooKeeperUtil.getInstance();
        this.sessionTimeout = this.zk.getSessionTimeout();

        this.createPathIfNotExists(QUEUE_ROOT);
        this.createPathIfNotExists(this.queuePath);

    }

    public void createPathIfNotExists(String path) {
        try {
            Stat stat = this.zk.exists(path, false);
            if (stat == null) {
                this.zk.create(path, ROOT_QUEUE_DATA, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void produce(byte[] data) throws Exception {
        this.createPathIfNotExists(this.queuePath);
        String node = this.zk.create(this.queuePath + "/", data, ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT_SEQUENTIAL);
        LOGGER.info("thread {} produce a message to queue: {} message data is: {}", Thread.currentThread().getName(),
                node, new String(data, "utf-8"));
    }

    public byte[] consume() throws Exception {
        List<String> nodes = null;
        byte[] result = null;
        Stat stat = null;
        do {
            synchronized(mutex) {
                nodes = this.zk.getChildren(this.queuePath, new ProduceWatcher());

                if (nodes == null || nodes.size() == 0) {
                    mutex.wait();
                } else {
                    SortedSet<String> sortedNode = new TreeSet<String>();
                    for (String node : nodes) {
                        sortedNode.add(queuePath + "/" + node);
                    }

                    String first = sortedNode.first();
                    result = this.zk.getData(first, false, stat);
                    this.zk.delete(first, -1);
                    LOGGER.info("thread {} consume a message from queue: {}, message data is : {}", Thread
                            .currentThread().getName(), first, new String(result, "utf-8"));
                    return result;
                }
            }
        } while (true);
    }

    private class ProduceWatcher implements Watcher {
        public void process(WatchedEvent watchedEvent) {
            synchronized(mutex) {
                mutex.notify();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        String queueName = "test";
        final ZooKeeperQueue queue = new ZooKeeperQueue(queueName);
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        queue.consume();
                        System.out.println("--------------------------------");
                        System.out.println();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000);
                        queue.produce(("message" + i).getBytes());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "produce-thread").start();
    }

}

