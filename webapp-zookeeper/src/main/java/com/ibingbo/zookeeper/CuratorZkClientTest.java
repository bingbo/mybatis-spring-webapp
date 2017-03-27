package com.ibingbo.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by bing on 2017/3/23.
 */
public class CuratorZkClientTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CuratorZkClientTest.class);

    private static CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181", 5000, 3000, new ExponentialBackoffRetry(1000, 3));


    public static void main(String[] args) throws Exception {
        final String path = "/curator_zk";
        client.start();

        client.delete().forPath(path);
        client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path);
        LOGGER.warn("nodes:{}",client.getChildren().forPath("/"));

        //节点数据变更监控
        final NodeCache nodeCache = new NodeCache(client, path, false);
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            public void nodeChanged() throws Exception {
                LOGGER.warn("nodes:{}", nodeCache.getCurrentData().getData());
            }
        });

        //节点变更监控
        final PathChildrenCache pathChildrenCache = new PathChildrenCache(client, path, true);
        pathChildrenCache.start();
        pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                switch (pathChildrenCacheEvent.getType()) {
                    case CHILD_ADDED:
                        LOGGER.warn("child added in {},children is {}", pathChildrenCacheEvent.getData().getPath(), curatorFramework.getChildren().forPath(path));
                        break;
                    case CHILD_REMOVED:
                        LOGGER.warn("child removed in {},children is {}", pathChildrenCacheEvent.getData().getPath(), curatorFramework.getChildren().forPath(path));
                        break;
                    case CHILD_UPDATED:
                        LOGGER.warn("child updated in {},children is {}", pathChildrenCacheEvent.getData().getPath(), curatorFramework.getChildren().forPath(path));
                        break;

                }
            }
        });

        client.setData().forPath(path, "hello,world".getBytes());
        byte[] data = client.getData().forPath(path);
        LOGGER.warn("node data:{}", new String(data));

        for (int i=0;i<10;i++) {
            client.create().withMode(CreateMode.PERSISTENT).forPath(path + "/node_" + i);
            Thread.sleep(1000);
        }
        for (int i=0;i<10;i++) {
            client.delete().forPath(path + "/node_" + i);
            Thread.sleep(1000);
        }

    }
}
