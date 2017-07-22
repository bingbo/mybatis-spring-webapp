package com.ibingbo.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by bing on 2017/3/9.
 */
public class Test implements Watcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(Test.class);

    private static final int TIMEOUT = 3000;
    //private static final String HOST = "127.0.0.1:2181";
    private static final String HOST = "10.94.239.21:8181";
    private static CountDownLatch latch = new CountDownLatch(1);
    private static ZooKeeper zooKeeper = null;
    private static ZooKeeper zooKeeper1 = null;

    public static void main(String[] args) {
        try {
            zooKeeper = new ZooKeeper(HOST, TIMEOUT, new Test());
            zooKeeper1 = new ZooKeeper(HOST, TIMEOUT, null);
            //为该会话添加权限控制
            zooKeeper.addAuthInfo("digest","bill:123".getBytes());

            //获取zookeeper连接状态
            System.out.println("state: " + zooKeeper.getState());

            try {
                latch.await();
            } catch (InterruptedException e) {
                System.out.println("zookeeper session established.");
            }

            //获取这次连接的会话ID及密码
            System.out.println("sessionId: " + zooKeeper.getSessionId());
            System.out.println("sessionPasswd: " + zooKeeper.getSessionPasswd());

            //判断是否存在某个节点及创建节点
            if (zooKeeper.exists("/zk_test", false) == null) {
                System.out.println("have no zk_test node");
                zooKeeper.create("/zk_test", "mydata".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT, new MyStringCallback(), "my contexxt");
            }


            //获取指定路径下的子节点
            List<String> children = zooKeeper.getChildren("/", new Test());
            LOGGER.info("all children: {}", children);

            //获取指定节点下的数据
            System.out.println("-------check node--------");
            Stat stat = new Stat();
            System.out.println(new String(zooKeeper.getData("/zk_test", true, stat)));
            LOGGER.error("czxid: {} mzxic: {} version: {}", stat.getCzxid(), stat.getMzxid(), stat.getVersion());

            //试图访问有权限的数据，没有权限会抛出异常KeeperErrorCode = NoAuth for /zk_test
            //LOGGER.info("********************");
            //zooKeeper1.getData("/zk_test", false, null);

            //更新指定节点下数据
            System.out.println("-------update node data-------");
            stat = zooKeeper.setData("/zk_test", "update data".getBytes(), -1);
            LOGGER.error("czxid: {} mzxic: {} version: {}", stat.getCzxid(), stat.getMzxid(), stat.getVersion());

            System.out.println("-------check node--------");
            zooKeeper.getData("/zk_test", true, new MyDataCallback(), null);

            //删除指定节点下的子节点，只能先删除子节点后，才能删除父节点
            System.out.println("--------delete node-------");
            zooKeeper.delete("/zk_test", -1);

            System.out.println("-----------check node is deleted-----");
            System.out.println("node status: " + zooKeeper.exists("/zk_test", false));

            int i=1;
            while (i<100) {
                zooKeeper1.setData("/zkclient", ("hello,world "+i).getBytes(),-1);
                i++;
                Thread.sleep(1000);
            }
            zooKeeper.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 监视节点变化的回调处理
     * @param watchedEvent
     */
    public void process(WatchedEvent watchedEvent) {
        LOGGER.info("watcher receive: {}", watchedEvent);
        try {
            if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                if (watchedEvent.getType() == Event.EventType.None && null == watchedEvent.getPath()) {
                    latch.countDown();
                } else if (watchedEvent.getType() == Event.EventType.NodeChildrenChanged) {
                    LOGGER.info("reget child: {}", zooKeeper.getChildren(watchedEvent.getPath(), true));
                } else if (watchedEvent.getType() == Event.EventType.NodeDataChanged) {
                    Stat stat = new Stat();
                    LOGGER.info("reget data:{}", zooKeeper.getData(watchedEvent.getPath(), true, stat));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 数据变化回调
     */
    static class MyDataCallback implements AsyncCallback.DataCallback {
        public void processResult(int resultCode, String path, Object context, byte[] data, Stat stat) {
            LOGGER.info("resultCode:{} path:{} context:{} data:{} stat:{}", resultCode, path, context, new String(data), stat);
        }
    }

    /**
     * 节点路径变化回调
     */
    static class MyStringCallback implements AsyncCallback.StringCallback {
        public void processResult(int resultCode, String path, Object context, String nodeName) {
            LOGGER.info("create path result: resultCode-{} , path-{}, context-{}, real path name-{}", resultCode, path, context, nodeName);
        }
    }

}
