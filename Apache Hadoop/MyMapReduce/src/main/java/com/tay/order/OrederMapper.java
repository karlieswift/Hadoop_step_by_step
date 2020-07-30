package com.tay.order;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author karlieswift
 * date: 2020/5/23 15:14
 * ClassName: OrederMapper
 * @version java "13.0.1"
 */
public class OrederMapper extends Mapper<LongWritable, Text,Order, NullWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] split = value.toString().split("\t");
        Order order=new Order(split[0],split[1], Double.parseDouble(split[2])) ;
        context.write(order,NullWritable.get());
    }
}
 