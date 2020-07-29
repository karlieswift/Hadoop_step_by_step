package com.tay.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * @author karlieswift
 * date: 2020/5/29 16:16
 * ClassName: ZooKeeperTest
 * @version java "13.0.1"
 */
public class ZooKeeperTest {
    public static void main(String[] args) throws Exception {


        ZooKeeperTest z=new ZooKeeperTest();
        z.init();
        z.delete_force("/taylor/karlie");
    }

    private String connectionString = "hadoop2:2181,hadoop3:2181,hadoop4:2181";
    private int sessionTimeout = 40000;  //session timeout in milliseconds 超时时间
    private ZooKeeper zooKeeper;


    @Before
    public void init() throws IOException {
        zooKeeper = new ZooKeeper(connectionString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                //process方法 ，当监听事件发生时，会启动该方法，
                //例如；当znode的节点方法变化，增删除修改，
            }
        });
    }

    /**
     * 关闭
     *
     * @throws InterruptedException
     */
    @After
    public void end() throws InterruptedException {
        zooKeeper.close();
    }


    @Test
    public void test1() throws Exception {

        System.out.println("zookeeper= " + zooKeeper);
    }


    @Test
    public void getchildren() throws KeeperException, InterruptedException {
        //方法介绍
        // List<String> getChildren(final String path, Watcher watcher) //获取path路径的子节点，通过watcher监听
        //  List<String> getChildren(String path, boolean watch)//获取path的子节点，false：不监听，true：默认的监听

//        List<String> children = zooKeeper.getChildren("/taylor", false);
//        for (String child : children) {
//            System.out.println(child);
//        }

        List<String> children = zooKeeper.getChildren("/taylor", new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("监听...........：" + event.toString());
            }
        });
        for (String child : children) {
            System.out.println(child);
        }

        Thread.sleep(10000);//监听10s,
    }


    @Test
    public void is_exists() throws KeeperException, InterruptedException {
        Stat exists = zooKeeper.exists("/taylor/karlie", false);//返回的是数据码
        if (exists == null) {
            System.out.println("不存在");
        } else {
            System.out.println("exists=" + exists + " 存在");//exists=12884901944,12884901944,1590747495831,1590747495831,0,0,0,0,5,0,12884901944
        }
    }


    //

    @Test
    public void get_data() throws KeeperException, InterruptedException {
        //先判断节点是否存在
        String path = "/taylor";
        Stat exists = zooKeeper.exists(path, false);
        if (exists == null) {
            return;
        } else {
            byte[] data = zooKeeper.getData(path, false, exists);
            System.out.println(new String(data));
        }
    }

    //获得节点信息
    public void get_data(String path) throws KeeperException, InterruptedException {
        //先判断节点是否存在

        Stat exists = zooKeeper.exists(path, false);
        if (exists == null) {
            return;
        } else {
            byte[] data = zooKeeper.getData(path, false, exists);
            System.out.println(new String(data));
        }
    }


    //创建节点
    @Test
    public void createfile() throws KeeperException, InterruptedException {
        String path = "/taylor/yarn";
        String s = zooKeeper.create(path, "hello yarn".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
        get_data("/taylor/yarn0000000013");
    }

//修改节点信息
    @Test
    public void set_Data() throws KeeperException, InterruptedException {
        Stat stat = zooKeeper.setData("/taylor", "hello taylor".getBytes(), -1);
    }



    //删除非空节点
    @Test
    public void delete_test() throws Exception {
        delete_force("/taylor");
    }

    private void delete_force(String path) throws Exception {
        if(zooKeeper.exists(path,false)==null){
            return;
        }
        List<String> children = zooKeeper.getChildren(path, false);
        if(children.size()==0){
            zooKeeper.delete(path, -1); //删除空节点
        }
        else {
            for (String child : children) {
                delete_force(path+ "/" + child);
            }
            delete_force(path);
        }
    }



}

























