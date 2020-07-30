package com.tay.partitionerWordCount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 *
 * @author karlieswift
 * date: 2020/5/23 10:08
 * ClassName: WordCountPartitioner
 * @version java "13.0.1"
 *
 *
 * 在Wordcount的基础上，将a-p 与 q-z的开头的单词分开输出
 *
 *数据格式如下：
 * taylor swift zoo
 * karlie opp Victory
 * beautiful Cent
 * taylor Swift
 *
 */
public class WordCountPartitioner extends Partitioner<Text, IntWritable> {

    @Override
    public int getPartition(Text text, IntWritable intWritable, int numPartitions) {
        int partiton;
       int comp= text.toString().compareToIgnoreCase("p");
        if(comp<=0){
            partiton=0;
        }
        else
            partiton=1;
        return partiton;
    }
}
 