package com.tay.hbase.api;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

/**
 * @author karlieswift
 * date: 2020/7/29 9:06
 * ClassName: HbaseAPIConn
 * @version java "13.0.1"
 * 创建namespace
 */
public class HbaseAPIConn {

    public static void main(String[] args) throws Exception {

        //todo 使用hbase api 的hbase，hbase是一个数据库，与mysql类似

        //todo 0-配置
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum","hadoop7,hadoop8,hadoop9");
        //todo 1-获取hbase的连接
        Connection connection = ConnectionFactory.createConnection(configuration);
        // todo 指定数据库的配置
        //    hbase在zk里存在节点，因此可以通过zk进行连接


        //todo 2-操作数据库

        //todo 3-关闭连接
        connection.close();
    }
}
 