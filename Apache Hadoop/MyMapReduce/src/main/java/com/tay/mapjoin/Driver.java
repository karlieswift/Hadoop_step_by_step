package com.tay.mapjoin;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author karlieswift
 * date: 2020/5/26 19:50
 * ClassName: Driver
 * @version java "13.0.1"
 */
public class Driver {
    public static void main(String[] args) throws IOException, URISyntaxException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job  = Job.getInstance(conf);

       job.addCacheFile(new URI("file:///D:/hadoop/join/pd.txt"));

        job.setJarByClass(Driver.class);
        job.setMapperClass(MapperJoin.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);



        String path="D:/hadoop/output";
        File file=new File(path);
        if(file.exists()){

            File[] files = file.listFiles();
            for (File file1 : files) {
                file1.delete();
            }
            file.delete();
        }

        FileInputFormat.setInputPaths(job,new Path("D:/hadoop/join/order.txt"));
        FileOutputFormat.setOutputPath(job,new Path(path));

        job.waitForCompletion(true);
    }
}
 