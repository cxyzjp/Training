package com.example.demo;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

/**
 * description:
 * author: bowen
 * date: 2019/6/4
 */
public class CuratorSampleTest {
    private static final String connectString = "192.168.2.21:2181,192.168.2.26:2181,192.168.2.27:2181";
    private CuratorFramework client;

//    @Before
//    public void init(){
//        client = CuratorFrameworkFactory.builder()
//                .connectString(connectString)
//                .sessionTimeoutMs(5000)
//                .connectionTimeoutMs(5000)
//                .retryPolicy(new RetryUntilElapsed(5000, 1000))
//                .build();
//        client.start();
//    }

    @Test
    public void getData() throws Exception {
        client = CuratorFrameworkFactory.builder()
                .connectString(connectString)
                .sessionTimeoutMs(60 * 1000)
                .connectionTimeoutMs(15 * 1000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();
        client.start();

        Stat stat = new Stat();
        byte[] ret = client.getData().storingStatIn(stat).forPath("/n2");
        System.out.println("====== get data: " + new String(ret));
        System.out.println("====== stat: " + stat);
    }

    @Test
    public void nodeListener() throws Exception {

    }
}
