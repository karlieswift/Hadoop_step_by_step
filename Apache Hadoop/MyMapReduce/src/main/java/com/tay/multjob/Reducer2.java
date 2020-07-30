package com.tay.multjob;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author karlieswift
 * date: 2020/5/26 22:11
 * ClassName: Reducer2
 * @version java "13.0.1"
 */
public class Reducer2 extends Reducer<Text,Text,Text, Text> {
    Text out_v=new Text();
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        String s="";
        for (Text value : values) {
            s=s+value.toString()+" ";
        }
        out_v.set(s);
        context.write(key,out_v);
    }
}
 