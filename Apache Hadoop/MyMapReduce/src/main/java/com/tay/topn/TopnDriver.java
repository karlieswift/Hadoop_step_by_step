package com.tay.topn;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.File;

/**
 * @author karlieswift
 * date: 2020/5/27 9:38
 * ClassName: TopnDriver
 * @version java "13.0.1"
 */
public class TopnDriver {

    public static void main(String[] args) throws Exception {
    Configuration configuration =new Configuration();
    Job job=Job.getInstance(configuration);

        job.setJarByClass(TopnDriver .class);

        job.setMapperClass(TopnMapper .class);
        job.setReducerClass(TopnReducer .class);

        job.setMapOutputKeyClass(Flow .class);
        job.setMapOutputValueClass(Text .class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Flow.class);




    String path="D:/hadoop/output";
    File file=new File(path);
        if(file.exists()){

        File[] files = file.listFiles();
        for (File file1 : files) {
            file1.delete();
        }
        file.delete();
    }

        FileInputFormat.setInputPaths(job,new Path("D:/hadoop/d.txt"));
        FileOutputFormat.setOutputPath(job,new Path(path));

        job.waitForCompletion(true);
}
}
 