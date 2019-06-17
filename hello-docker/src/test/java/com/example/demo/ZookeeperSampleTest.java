package com.example.demo;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * description:
 * author: bowen
 * date: 2019/6/4
 */
public class ZookeeperSampleTest {
    private static final String connectString = "192.168.2.21:2181,192.168.2.26:2181,192.168.2.27:2181";
    private static ZooKeeper zkClient = null;
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    @Before
    public void init() throws Exception {

        zkClient = new ZooKeeper(connectString, 5000, event -> {
            System.out.println("====== zk event:" + event.getState());
            if (Watcher.Event.KeeperState.SyncConnected == event.getState()) {  //zk连接成功通知事件
                System.out.println("====== zk msg: " + event.getType() + " " + event.getPath());

                if (Watcher.Event.EventType.None == event.getType() && null == event.getPath()) {
                    connectedSemaphore.countDown();
                }
            }
        });
        connectedSemaphore.await();

        List<String> children = zkClient.getChildren("/", true);
        System.out.println("====== " + children);
        System.out.println("====== connected success!");
    }

    // 创建数据节点到zk中
    @Test
    public void testCreate() throws KeeperException, InterruptedException {
        // 参数1：要创建的节点的路径 参数2：节点数据 参数3：节点的权限 参数4：节点的类型
        zkClient.create("/n1", "n1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        //上传的数据可以是任何类型，但都要转成byte[]
        System.out.println("node create success！");
    }

    @Test
    public void testDelete() throws KeeperException, InterruptedException {
        zkClient.delete("/znode01", -1);
        System.out.println("====== node delete success！");
    }

    //判断znode是否存在
    @Test
    public void testExist() throws Exception {
        Stat stat = zkClient.exists("/n1", false);
        System.out.println("====== not exist"  + stat);
    }

    // 获取子节点
    @Test
    public void getChildren() throws Exception {
        List<String> children = zkClient.getChildren("/", true);
        for (String child : children) {
            System.out.println(child);
        }
        Thread.sleep(Long.MAX_VALUE);
    }

    //获取znode的数据
    @Test
    public void getData() throws Exception {
        byte[] data = zkClient.getData("/n1", false, null);
        System.out.println(new String(data));
    }

    @Test
    public void setData() throws Exception {
        zkClient.setData("/n1", "n1-a".getBytes(), -1);
        byte[] data = zkClient.getData("/n1", false, null);
        System.out.println(new String(data));
    }
}
