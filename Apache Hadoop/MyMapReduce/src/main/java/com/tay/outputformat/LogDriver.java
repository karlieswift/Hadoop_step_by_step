package com.tay.outputformat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.File;

/**
 * @author karlieswift
 * date: 2020/5/25 10:16
 * ClassName: LogDriver
 * @version java "13.0.1"
 */
public class LogDriver{
    public static void main(String[] args) throws Exception{
        Configuration configuration=new Configuration();
        Job job= Job.getInstance(configuration);

        job.setMapperClass(LogMaper.class);
        job.setReducerClass(LogReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

       job.setOutputFormatClass(LogOutPutFormat.class);

        String path="D:/hadoop/output";
        File file=new File(path);
        if(file.exists()){

            File[] files = file.listFiles();
            for (File file1 : files) {
                file1.delete();
            }
            file.delete();
        }

        FileInputFormat.setInputPaths(job,new Path("D:/hadoop/log.txt"));
        FileOutputFormat.setOutputPath(job,new Path(path));

        job.waitForCompletion(true);




    }
}
 