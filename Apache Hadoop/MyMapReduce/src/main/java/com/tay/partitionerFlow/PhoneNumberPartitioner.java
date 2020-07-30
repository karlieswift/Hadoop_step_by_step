package com.tay.partitionerFlow;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * @author karlieswift
 * date: 2020/5/23 9:28
 * ClassName: PhoneNumberPartitioner
 * @version java "13.0.1"
 *
 *
 * 对数据进行分区 输出
 * 136--p0
 * 137--p1
 * 138--p2
 * 139--p3
 * ohter--p4
 *
 * 个别数据格式
 * 7 	13590439668	192.168.100.4		        	1116	954	    200
 * 8 	15910133277	192.168.100.5	www.hao123.com	3156	2936	200
 * 9 	13729199489	192.168.100.6	        		240	       0	200
 *
 */
public class PhoneNumberPartitioner extends Partitioner<Text,FlowBean> {
    @Override
    public int getPartition(Text text, FlowBean flowBean, int numPartitions) {
        int partiton;
        //获取手机号
        String phonenumber=text.toString();
        if(phonenumber.startsWith("136")){
            partiton=0;
        }
        if(phonenumber.startsWith("137")){
            partiton=1;
        }
        if(phonenumber.startsWith("138")){
            partiton=2;
        }
        if(phonenumber.startsWith("139")){
            partiton=3;
        }
        else
            partiton=4;
        return partiton;
    }
}
 