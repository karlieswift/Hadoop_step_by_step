package com.tay.topn1;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.TreeMap;

/**
 * @author karlieswift
 * date: 2020/5/27 12:02
 * ClassName: Mapper1
 * @version java "13.0.1"
 *
 *
 *
 * 12 323 423
 * 12 32 21 321
 * 12323 12
 */
public class Mapper1 extends Mapper<LongWritable, Text, NullWritable, IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        TreeMap<Integer,String> list=new TreeMap<>();
        String[] s = value.toString().split(" ");
        for (String s1 : s) {
            list.put(Integer.parseInt(s1),"");
            if(list.size()>10){
                list.remove(list.firstKey());
            }
        }


        for(Integer i:list.keySet()){
            context.write(NullWritable.get(),new IntWritable(i));
        }
    }
}
 