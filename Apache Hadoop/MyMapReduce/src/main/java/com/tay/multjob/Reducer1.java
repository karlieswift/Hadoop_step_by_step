package com.tay.multjob;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author karlieswift
 * date: 2020/5/26 22:11
 * ClassName: Reducer1
 * @version java "13.0.1"
 */
public class Reducer1 extends Reducer<Text, IntWritable,Text,IntWritable> {
    IntWritable out_v=new IntWritable();
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum=0;
        for (IntWritable value : values) {
            sum=sum+value.get();
        }
        out_v.set(sum);
        context.write(key,out_v);
    }
}
 