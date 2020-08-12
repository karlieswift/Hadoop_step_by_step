package com.tay.hbase.api;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;


/**
 * @author karlieswift
 * date: 2020/7/29 9:38
 * ClassName: HbaseScanData
 * @version java "13.0.1"
 *  添加数据table
 */
public class HbaseScanData {
    public static void main(String[] args) throws Exception{

        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum","hadoop7,hadoop8,hadoop9");

        Connection connection = ConnectionFactory.createConnection(configuration);

        Admin admin = connection.getAdmin();



        TableName tableName = TableName.valueOf("taylor", "stu");
        if(admin.tableExists(tableName )){

            Table table = connection.getTable(tableName);
            Put put = new Put(Bytes.toBytes("1002"));
            put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("age"), Bytes.toBytes("22"));
            table.put(put);

            Scan scan=new Scan();
            Scan scan1 = scan.withStartRow(Bytes.toBytes("1001")).withStopRow(Bytes.toBytes("1003"));
            ResultScanner scanner = table.getScanner(scan1);
            for (Result result : scanner) {
                Cell[] cells = result.rawCells();
                byte[] row = result.getRow();
                for (Cell cell : cells) {
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
 