package com.tay.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * @author karlieswift
 * date: 2020/5/30 12:13
 * ClassName: Server
 * @version java "13.0.1"
 */
public class Server {

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        server.initnode();
        server.initserver();
        server.registerServer(args[0],args[1]);
        Thread.sleep(Long.MAX_VALUE);
    }


    private String connectionString="hadoop2:2181,hadoop3:2181,hadoop4:2181";
    private int timeout=400000;
    private ZooKeeper zooKeeper;
    private final String path="/servers";
    /**
     * init znode
     * @throws IOException
     */
    public void initnode() throws IOException {
        zooKeeper=new ZooKeeper(connectionString,timeout,event -> {});
    }
    /**
     * init servers
     * @throws KeeperException
     * @throws InterruptedException
     */
    public void initserver() throws KeeperException, InterruptedException {
        String s="";
        if(zooKeeper.exists(path,false)==null){
            s = zooKeeper.create(path, "servers node".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
    }
    /**
     * register childnodeserver
     * @param name  :other server'name
     * @param content :this server's contents
     * @throws KeeperException
     * @throws InterruptedException
     */
    public void registerServer(String name,String content) throws KeeperException, InterruptedException {
        String childpath=path+"/"+name;
        String s="";
        if(zooKeeper.exists(childpath,false)==null){
            s = zooKeeper.create(childpath,content.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        System.out.println(s+"is living.....");
    }


}
 