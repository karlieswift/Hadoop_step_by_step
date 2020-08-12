package com.tay.hbase.api;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;


/**
 * @author karlieswift
 * date: 2020/7/29 9:38
 * ClassName: HbaseCreatetable
 * @version java "13.0.1"
 *  创建table
 */
public class HbaseCreatetable {
    public static void main(String[] args) throws Exception{

        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum","hadoop7,hadoop8,hadoop9");

        Connection connection = ConnectionFactory.createConnection(configuration);

        Admin admin = connection.getAdmin();

        //todo 创建table

        TableName tableName = TableName.valueOf("taylor", "stu");
        if(admin.tableExists(tableName )){
            System.out.println("存在");
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
 