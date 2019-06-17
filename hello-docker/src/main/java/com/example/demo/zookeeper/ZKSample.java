package com.example.demo.zookeeper;

import org.apache.zookeeper.ZooKeeper;

/**
 * description:
 * author: bowen
 * date: 2019/6/4
 */
public class ZKSample {

    public static void main(String[] args) throws Exception {
        ZooKeeper session = createSession();
        if(session != null){
            session.getChildren("/", true);
        }
    }

    private static ZooKeeper createSession() throws Exception {
        ZooKeeper zk = new ZooKeeper("192.168.2.21:2181", 5000, watchedEvent -> {
            System.out.println("zk 1: " + watchedEvent.getType() + " --- " + watchedEvent.getPath());
        });
        System.out.println("conneted ok!");
        return zk;
    }

}
