package com.tay.multjob;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * @author karlieswift
 * date: 2020/5/26 22:11
 * ClassName: Mapper1
 * @version java "13.0.1"
 */
public class Mapper1 extends Mapper<LongWritable, Text,Text, IntWritable> {
    Text out_key=new Text();
    IntWritable out_v=new IntWritable(1);
    String str="";

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {

        FileSplit fileSplit= (FileSplit) context.getInputSplit();
        str=fileSplit.getPath().getName();

    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] split = value.toString().split(" ");
        for (String s : split) {
            out_key.set(s+"--"+str);
            context.write(out_key,out_v);
        }
    }
}
 