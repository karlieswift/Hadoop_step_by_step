package com.tay.hbase.api;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

/**
 * @author karlieswift
 * date: 2020/7/29 9:38
 * ClassName: HbasetableExists
 * @version java "13.0.1"
 * 判断table 是否存在
 */
public class HbasetableExists {
    public static void main(String[] args) throws Exception{

        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum","hadoop7,hadoop8,hadoop9");

        Connection connection = ConnectionFactory.createConnection(configuration);

        Admin admin = connection.getAdmin();

        boolean b = admin.tableExists(TableName.valueOf("test:emp"));
        if(b){
            System.out.println("存在");
        }else {
            System.out.println("不存在");
        }


        admin.close();
        connection.close();

    }
}
 