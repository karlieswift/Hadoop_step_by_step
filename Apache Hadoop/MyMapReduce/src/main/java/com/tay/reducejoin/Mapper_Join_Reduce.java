package com.tay.reducejoin;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * @author karlieswift
 * date: 2020/5/26 11:07
 * ClassName: Mapper_Join_Reduce
 * @version java "13.0.1"
 */
public class Mapper_Join_Reduce extends Mapper<LongWritable , Text,Text,Order> {

    private FileSplit current_fileSplit ;
    Text out_key=new Text();
    Order order=new Order();
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {

        InputSplit inputSplit = context.getInputSplit();
        current_fileSplit=(FileSplit) inputSplit;
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] split = value.toString().split("\t");


        String name = current_fileSplit.getPath().getName();
        if ("order.txt".equals(name)) {

            out_key.set(split[1]);
            order.setId(split[0]);
            order.setPid(split[1]);
            order.setAmount(Integer.parseInt(split[2]));
            order.setPname("");
            order.setFlag("order");

        }else {
            out_key.set(split[0]);
            order.setId("");
            order.setPid(split[0]);
            order.setAmount(0);
            order.setPname(split[1]);
            order.setFlag("pd");
        }

        context.write(out_key,order);
    }
}
 