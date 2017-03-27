package com.ibingbo.zookeeper;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.IZkStateListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by bing on 2017/3/23.
 */
public class ZkClientTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ZkClientTest.class);

    public static void main(String[] args) throws InterruptedException {
        ZkClient zkClient = new ZkClient("127.0.0.1:2181", 5000);
        LOGGER.warn("zookeeper session established");
        String path = "/zkclient";

        zkClient.subscribeChildChanges("/zkclient", new IZkChildListener() {
            public void handleChildChange(String parentPath, List<String> currentChildren) throws Exception {
                LOGGER.warn("parent: {}, children: {}", parentPath, currentChildren);
            }
        });
        zkClient.subscribeDataChanges(path, new IZkDataListener() {
            public void handleDataChange(String s, Object o) throws Exception {
                LOGGER.info("path:{},new data:{}", s, o);
            }

            public void handleDataDeleted(String s) throws Exception {
                LOGGER.info("path:{} 's data deleted", s);
            }
        });
        zkClient.subscribeStateChanges(new IZkStateListener() {
            public void handleStateChanged(Watcher.Event.KeeperState keeperState) throws Exception {
                LOGGER.warn("state:{}", keeperState);
            }

            public void handleNewSession() throws Exception {
                LOGGER.warn("new session");
            }

            public void handleSessionEstablishmentError(Throwable throwable) throws Exception {
                LOGGER.warn("new session error",throwable);
            }
        });

        zkClient.delete(path+"/c1");
        zkClient.delete(path);

        zkClient.createPersistent(path);
        LOGGER.warn("children:{}",zkClient.getChildren(path));
        Thread.sleep(1000);

        zkClient.createPersistent("/zkclient/c1", true);
        LOGGER.warn("children:{}",zkClient.getChildren(path));
        Thread.sleep(1000);

        LOGGER.warn("path:{},data:{}", path, zkClient.readData(path));
        zkClient.writeData(path, "hello,bill");
        Thread.sleep(Integer.MAX_VALUE);

        //zkClient.delete(path+"/c1");
        //zkClient.delete(path);



    }
}
