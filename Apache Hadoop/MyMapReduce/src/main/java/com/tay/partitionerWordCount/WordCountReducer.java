package com.tay.partitionerWordCount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author karlieswift
 * date: 2020/5/23 9:49
 * ClassName: WordCountReducer
 * @version java "13.0.1"
 */


/**
 * WordCountReducer继承类public class Reducer<KEYIN,VALUEIN,KEYOUT,VALUEOUT>
 * KEYIN:来自maper的输出key
 * VALUEIN:来自mapper输出value
 * KEYOUT:reduce输出的key
 * VALUEOUT:reduce输出的value
 */
class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    private IntWritable result = new IntWritable();

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum = 0;
        for (IntWritable value : values) {
            sum += value.get();
        }
        result.set(sum);
        context.write(key, result);
    }
}
 