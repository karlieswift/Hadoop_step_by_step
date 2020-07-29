package com.tay.hive.etl;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author karlieswift
 * date: 2020/6/7 16:12
 * ClassName: EtlMapper
 * @version java "13.0.1"
 */
public class EtlMapper extends Mapper<LongWritable, Text,Text, NullWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String s = value.toString();
        String etldata = EtlUtils.etldata(s);
        if(etldata==null){
            return;
        }
        context.write(new Text(etldata),NullWritable.get());
    }
}
 