package com.tay.topn1;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * @author karlieswift
 * date: 2020/5/27 12:02
 * ClassName: Reducer1
 * @version java "13.0.1"
 */
public class Reducer1 extends Reducer<NullWritable, IntWritable,IntWritable,NullWritable> {

    TreeMap<Integer,String> map=new TreeMap<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2-o1;
        }
    });

    @Override
    protected void reduce(NullWritable key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        for (IntWritable value : values) {
            map.put(Integer.parseInt(value.toString()),"");
            if(map.size()>10){
                map.remove(map.lastKey());
            }
        }
    }
    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {

        for(Integer i:map.keySet()){
            context.write(new IntWritable(i),NullWritable.get());
        }
    }
}
 