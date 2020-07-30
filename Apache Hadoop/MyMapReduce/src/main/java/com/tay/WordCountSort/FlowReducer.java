package com.tay.WordCountSort;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author karlieswift
 * date: 2020/5/22 12:50
 * ClassName: FlowReducer
 * @version java "13.0.1"
 */
public class FlowReducer extends Reducer<FlowBean,Text,Text,FlowBean> {


    @Override
    protected void reduce(FlowBean key, Iterable<Text> values, Context context) throws IOException, InterruptedException {


        for (Text value : values) {
            context.write(value,key);
        }
    }
}
 