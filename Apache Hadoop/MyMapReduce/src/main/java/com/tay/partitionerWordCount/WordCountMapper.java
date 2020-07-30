package com.tay.partitionerWordCount;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
/**
 * @author karlieswift
 * date: 2020/5/23 9:49
 * ClassName: WordCountMapper
 * @version java "13.0.1"
 */

/**
 * WordCountMapper类继承public class Mapper<KEYIN, VALUEIN, KEYOUT, VALUEOUT>
 * KEYIN:代表输入的偏移量,
 * VALUEIN:代表输入的文本的一行,------Test
 * KEYOUT:Mapper输出的key
 * VALUEOUT:Mapper输出的value
 */
class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    private Text out_key = new Text();
    private final static IntWritable out_value = new IntWritable(1);

    /**
     * 文本文件有 n行，map函数调用 n次
     * @param key
     * @param value
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String str = value.toString();
        String[] words = str.split(" ");
        for (String word : words) {
            out_key.set(word);  //输出的k-v=word:1
            context.write(out_key, out_value);
        }
    }
}
