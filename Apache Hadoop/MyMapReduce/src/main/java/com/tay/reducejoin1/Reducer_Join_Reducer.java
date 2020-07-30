package com.tay.reducejoin1;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author karlieswift
 * date: 2020/5/26 11:36
 * ClassName: Reducer_Join_Reducer
 * @version java "13.0.1"
 */
public class Reducer_Join_Reducer extends Reducer<Order,NullWritable,Order, NullWritable> {

    Order order =new Order();

    @Override
    protected void reduce(Order key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {

        int i=1;

        for (NullWritable value : values) {

            if(i==1){
                i++;
                order.setPname(key.getPname());
            }else{
                key.setPname(order.getPname());
               context.write(key,NullWritable.get());
            }

            ;
            }


    }
}
 