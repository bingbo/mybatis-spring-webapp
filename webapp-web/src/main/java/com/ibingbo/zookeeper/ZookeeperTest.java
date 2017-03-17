package com.ibingbo.zookeeper;

import org.apache.zookeeper.*;

/**
 * Created by bing on 2017/3/9.
 */
public class ZookeeperTest implements Watcher {

    private static final int TIMEOUT = 3000;
    private static final String HOST = "127.0.0.1:2181";

    public static void main(String[] args) {
        try {
            ZooKeeper zooKeeper = new ZooKeeper(HOST, TIMEOUT, new ZookeeperTest());
            if (zooKeeper.exists("/zk_test", false) == null) {
                System.out.println("have no zk_test node");
                zooKeeper.create("/zk_test", "mydata".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }

            System.out.println("-------check node--------");
            System.out.println(new String(zooKeeper.getData("/zk_test", false, null)));

            System.out.println("-------update node data-------");
            zooKeeper.setData("/zk_test", "update data".getBytes(), -1);

            System.out.println("-------check node--------");
            System.out.println(new String(zooKeeper.getData("/zk_test", false, null)));

            System.out.println("--------delete node-------");
            zooKeeper.delete("/zk_test", -1);

            System.out.println("-----------check node is deleted-----");
            System.out.println("node status: " + zooKeeper.exists("/zk_test", false));

            zooKeeper.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("receive notify: " + watchedEvent.getState());
    }
}
