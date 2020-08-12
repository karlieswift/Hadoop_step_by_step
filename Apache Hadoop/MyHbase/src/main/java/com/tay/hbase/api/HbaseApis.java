package com.tay.hbase.api;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * @author karlieswift
 * date: 2020/7/29 11:38
 * ClassName: HbaseApis
 * @version java "13.0.1"
 * 对前面的api进行练习
 */
public class HbaseApis {
    public static void main(String[] args) throws IOException {
        //todo 设置配置，创立连接
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum","hadoop7,hadoop8,hadoop9");
        Connection connection = ConnectionFactory.createConnection(configuration);
        Admin admin = connection.getAdmin();
//        NamespaceDescriptor test1 = NamespaceDescriptor.create("test1").build();
//        admin.createNamespace(test1);

        TableName tableName = TableName.valueOf(Bytes.toBytes("test1"), Bytes.toBytes("stu"));

        if(admin.tableExists(tableName)){
            //todo 添加数据
            Table table = connection.getTable(tableName);
            Put put = new Put(Bytes.toBytes("1001"));
            put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("name"),Bytes.toBytes("taylor"));
            table.put(put);


            //todo 扫描数据
            Scan scan=new Scan().withStartRow(Bytes.toBytes("1001")).withStopRow(Bytes.toBytes("1003"));
            ResultScanner results = table.getScanner(scan);
            for (Result result : results) {
                Cell[] cells = result.rawCells();
                for (Cell cell : cells) {
                    byte[] row = result.getRow();
                    System.out.println("rowKey:"+Bytes.toString(row));
                    byte[] info = CellUtil.cloneFamily(cell);
                    byte[] name = CellUtil.cloneQualifier(cell);
                    byte[] value = CellUtil.cloneValue(cell);

                    System.out.println("info:"+Bytes.toString(info));
                    System.out.println("name:"+Bytes.toString(name) );
                    System.out.println("vaule:"+Bytes.toString(value) );
                    System.out.println("===========================================");
                }
            }
            table.close();
        }
        else {
            //todo 创建table
            TableDescriptorBuilder stu = TableDescriptorBuilder.newBuilder(tableName);
            ColumnFamilyDescriptorBuilder info = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes("info"));
            TableDescriptor build = stu.setColumnFamily(info.build()).build();
            admin.createTable(build);

        }

        //todo 删除table
        admin.disableTable(tableName);
        admin.deleteTable(tableName);

        admin.deleteNamespace("test1");
        admin.close();
        connection.close();

    }
}
 