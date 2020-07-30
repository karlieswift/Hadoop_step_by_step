package com.tay.multjob;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author karlieswift
 * date: 2020/5/26 22:11
 * ClassName: Mapper2
 * @version java "13.0.1"
 *
 *
 */
public class Mapper2  extends Mapper<LongWritable, Text,Text, Text> {
    Text out_k=new Text();
    Text out_v=new Text();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String[] split = value.toString().split("--");
        String[] s = split[1].split("\t");
        out_k.set(split[0]);
        out_v.set(s[0]+"-->"+s[1]);
            context.write(out_k,out_v);

    }
}
 