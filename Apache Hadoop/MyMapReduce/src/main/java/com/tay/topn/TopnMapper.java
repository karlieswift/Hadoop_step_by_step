package com.tay.topn;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.TreeMap;

/**
 * @author karlieswift
 * date: 2020/5/27 9:38
 * ClassName: TopnMapper
 * @version java "13.0.1"
 */
public class TopnMapper extends Mapper<LongWritable, Text,Flow, Text> {

    TreeMap<Flow,String> list=new TreeMap<>();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {


        Flow out_value=new Flow();
        // 137 1 2  3
        String[] split = value.toString().split("\t");
        out_value.setUp_flow(Integer.parseInt(split[1]));
        out_value.setDown_flow(Integer.parseInt(split[2]));
        out_value.setSum_flow(out_value.getDown_flow()+out_value.getUp_flow());
        list.put(out_value,split[0]);
        if(list.size()>10){
            list.remove(list.lastKey());
        }

    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        for(Flow i:list.keySet()){
            context.write(i,new Text(list.get(i)));
        }
    }
}
 