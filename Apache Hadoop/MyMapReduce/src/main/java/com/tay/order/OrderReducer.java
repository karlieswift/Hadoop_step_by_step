package com.tay.order;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author karlieswift
 * date: 2020/5/23 15:21
 * ClassName: OrderReducer
 * @version java "13.0.1"
 */
public class OrderReducer extends Reducer<Order, NullWritable,Order,NullWritable> {
    static double max=-1;
    @Override
    protected void reduce(Order key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        int i=0;
        double max=0;
        for (NullWritable value : values) {
            if(i==0){
                context.write(key,NullWritable.get());
                max=key.price;
                i++;
            }
            else if(max==key.price){
                context.write(key,NullWritable.get());
            }
        }
    }
}
 