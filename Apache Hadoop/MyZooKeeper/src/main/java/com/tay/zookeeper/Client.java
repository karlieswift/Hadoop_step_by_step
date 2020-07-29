package com.tay.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;

/**
 * @author karlieswift
 * date: 2020/5/30 12:13
 * ClassName: Client
 * @version java "13.0.1"
 */
public class Client {

    public static void main(String[] args) throws Exception {
        Client client = new Client();
        client.initclient();
        client.getliving_servers();


        Thread.sleep(Long.MAX_VALUE);
    }



    private String connectionString="hadoop2:2181,hadoop3:2181,hadoop4:2181";
    private int timeout=400000;
    private ZooKeeper zooKeeper;
    private final String path="/servers";

    public void initclient() throws IOException {
        zooKeeper=new ZooKeeper(connectionString,timeout,event -> {});
    }

    public void getliving_servers() throws KeeperException, InterruptedException {
        List<String> children = zooKeeper.getChildren(path, event -> {
            try {
                //when server's nodes were changed ,client renew into getliving_servers method,
                getliving_servers();
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        System.out.println(children);
    }
}
 