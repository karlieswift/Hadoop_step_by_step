package com.tay.topn1;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.File;

/**
 * @author karlieswift
 * date: 2020/5/27 13:11
 * ClassName: Driver1
 * @version java "13.0.1"
 */
public class Driver1 {

    public static void main(String[] args) throws Exception{
        Configuration configuration =new Configuration();
        Job job=Job.getInstance(configuration);

        job.setJarByClass(Driver1.class);

        job.setMapperClass(Mapper1.class);
        job.setReducerClass(Reducer1.class);

        job.setMapOutputKeyClass(NullWritable.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(NullWritable.class);

        String path="D:/hadoop/output";
        File file=new File(path);
        if(file.exists()){

            File[] files = file.listFiles();
            for (File file1 : files) {
                file1.delete();
            }
            file.delete();
        }

        FileInputFormat.setInputPaths(job,new Path("D:/hadoop/topn"));
        FileOutputFormat.setOutputPath(job,new Path(path));

        job.waitForCompletion(true);
    }
}

 