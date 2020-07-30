package com.tay.WordCountSort;

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
public class FlowMapper extends Mapper<LongWritable, Text, FlowBean,Text> {

   private FlowBean out_key=new FlowBean();
   private Text out_vaule=new Text();


    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] split = value.toString().split("\t");
        out_vaule.set(split[1]);
        out_key.setUp_flow(Integer.parseInt(split[split.length-3]));
        out_key.setDown_flow(Integer.parseInt(split[split.length-2]));
        out_key.setSum_flow(out_key.getDown_flow()+out_key.getUp_flow());
        context.write(out_key,out_vaule);
    }
}
 