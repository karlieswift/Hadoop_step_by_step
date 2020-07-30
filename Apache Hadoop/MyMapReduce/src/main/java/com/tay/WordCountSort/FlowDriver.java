package com.tay.WordCountSort;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.File;

/**
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
 * @author karlieswift
 * date: 2020/5/22 13:01
 * ClassName: FlowDriver
 * @version java "13.0.1"
 */
public class FlowDriver {
    public static void main(String[] args) throws Exception {

        Configuration configuration =new Configuration();
        Job job=Job.getInstance(configuration);

        job.setJarByClass(FlowDriver.class);

        job.setMapperClass(FlowMapper.class);
        job.setReducerClass(FlowReducer.class);

        job.setMapOutputKeyClass(FlowBean.class);
        job.setMapOutputValueClass(Text.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);

        String path="D:/hadoop/output";
        File file=new File(path);
        if(file.exists()){

            File[] files = file.listFiles();
            for (File file1 : files) {
                file1.delete();
            }
            file.delete();
        }

        FileInputFormat.setInputPaths(job,new Path("D:/hadoop/input"));
        FileOutputFormat.setOutputPath(job,new Path(path));

        job.waitForCompletion(true);
    }
}
 