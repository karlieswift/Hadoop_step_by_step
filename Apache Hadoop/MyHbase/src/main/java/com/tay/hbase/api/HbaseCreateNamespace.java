package com.tay.hbase.api;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.NamespaceDescriptor;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

/**
 * @author karlieswift
 * date: 2020/7/29 9:06
 * ClassName: HbaseCreateNamespace
 * @version java "13.0.1"
 * 创建namespace
 */
public class HbaseCreateNamespace {

    public static void main(String[] args) throws Exception {
        //设置配置
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum","hadoop7,hadoop8,hadoop9");


        //获取连接
        Connection connection = ConnectionFactory.createConnection(configuration);
        //todo 获取hbase 的权限
        Admin admin = connection.getAdmin();
        //todo 创建namespace
        NamespaceDescriptor nd = NamespaceDescriptor.create("taylor").build();
        admin.createNamespace(nd);
        //删除
//        admin.deleteNamespace("taylor");


        //关闭连接
        admin.close();
        connection.close();
    }
}
 