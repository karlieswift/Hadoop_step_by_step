package com.tay.writable;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author karlieswift
 * date: 2020/5/22 12:37
 * ClassName: FlowMapper
 * @version java "13.0.1"
 */
public class FlowMapper extends Mapper<LongWritable, Text, Text,FlowBean> {

   private FlowBean out_value=new FlowBean();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] split = value.toString().split("\t");
        value.set(split[1]);
        out_value.setUp_flow(Integer.parseInt(split[split.length-3]));
        out_value.setDown_flow(Integer.parseInt(split[split.length-2]));
        out_value.setSum_flow(out_value.getDown_flow()+out_value.getUp_flow());
        context.write(value,out_value);
    }
}
 