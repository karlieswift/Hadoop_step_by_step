package com.tay.reducejoin;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author karlieswift
 * date: 2020/5/26 11:36
 * ClassName: Reducer_Join_Reducer
 * @version java "13.0.1"
 */
public class Reducer_Join_Reducer extends Reducer<Text,Order,Order, NullWritable> {

    Order order =new Order();

    @Override
    protected void reduce(Text key, Iterable<Order> values, Context context) throws IOException, InterruptedException {

        List<Order> list = new ArrayList<>();
        for (Order value : values) {
            if ("order".equals(value.getFlag())) {
                Order temp=new Order();
                try {
                    BeanUtils.copyProperties(temp,value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                list.add(temp);
            } else {
                order.setPname(value.getPname());
            }

        }

        for (Order order1 : list) {
            order1.setPname(order.getPname());
            context.write(order1, NullWritable.get());
        }
    }
}
 