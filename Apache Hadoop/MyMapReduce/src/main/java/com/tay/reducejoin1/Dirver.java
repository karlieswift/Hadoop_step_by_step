package com.tay.reducejoin1;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.File;

/**
 * @author karlieswift
 * date: 2020/5/26 11:58
 * ClassName: Dirver
 * @version java "13.0.1"
 */
public class Dirver {
    public static void main(String[] args) throws Exception{
        Configuration configuration =new Configuration();
        Job job=Job.getInstance(configuration);

        job.setJarByClass(Dirver.class);

        job.setMapperClass(Mapper_Join_Reduce.class);
        job.setReducerClass(Reducer_Join_Reducer.class);

        job.setMapOutputKeyClass(Order.class);
        job.setMapOutputValueClass(NullWritable.class);

        job.setOutputKeyClass(Order.class);
        job.setOutputValueClass(NullWritable.class);

       job.setGroupingComparatorClass(grouping.class);
        String path="D:/hadoop/output";
        File file=new File(path);
        if(file.exists()){

            File[] files = file.listFiles();
            for (File file1 : files) {
                file1.delete();
            }
            file.delete();
        }

        FileInputFormat.setInputPaths(job,new Path("D:/hadoop/join"));
        FileOutputFormat.setOutputPath(job,new Path(path));

        job.waitForCompletion(true);
    }
}
 