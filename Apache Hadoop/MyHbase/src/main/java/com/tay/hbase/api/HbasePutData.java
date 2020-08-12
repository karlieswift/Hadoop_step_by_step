package com.tay.hbase.api;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.ArrayList;
import java.util.List;


/**
 * @author karlieswift
 * date: 2020/7/29 9:38
 * ClassName: HbasePutData
 * @version java "13.0.1"
 *  添加数据table
 */
public class HbasePutData {
    public static void main(String[] args) throws Exception{

        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum","hadoop7,hadoop8,hadoop9");

        Connection connection = ConnectionFactory.createConnection(configuration);

        Admin admin = connection.getAdmin();



        TableName tableName = TableName.valueOf("taylor", "stu");
        if(admin.tableExists(tableName )){
            System.out.println("存在+添加数据");
            Table table = connection.getTable(tableName);
            List<Put> puts = new ArrayList<>();
            Put put = new Put(Bytes.toBytes("1002"));
            put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("name"), Bytes.toBytes("kaka"));
            puts.add(put);
            table.put(puts);  //添加多个数据
//            Put put = new Put(Bytes.toBytes("1001"));
//            put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("name"), Bytes.toBytes("kaka"));
//            table.put(put);////添加一个数据
            table.close();
        }else {
            TableDescriptorBuilder td = TableDescriptorBuilder.newBuilder(tableName);
            String str="info";
            byte[] bytes = Bytes.toBytes(str);
            ColumnFamilyDescriptorBuilder cd = ColumnFamilyDescriptorBuilder.newBuilder(bytes);
            TableDescriptor build = td.setColumnFamily(cd.build()).build();
            admin.createTable(build);
        }


        admin.close();
        connection.close();

    }
}
 