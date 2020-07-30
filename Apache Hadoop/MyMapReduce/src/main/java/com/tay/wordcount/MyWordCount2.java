package com.tay.wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.File;

/**
 *
 * 本地测试
 * MyWordCount2主要测试的是本地统计文本文件的单词出现频率
 * @author karlieswift
 * date: 2020/5/20 19:27
 * ClassName: MyWordCount
 * @version java "13.0.1"
 */
public class MyWordCount2 {
    /**
     * 该main方法是该mapreduce程序运行的入口，其中用一个Job类对象来管理程序运行时所需要的很多参数：
     * 比如，指定用哪个组件作为数据读取器、数据结果输出器 指定用哪个类作为map阶段的业务逻辑类，哪个类作为reduce阶段的业务逻辑类
     * 指定wordcount job程序的jar包所在路径 .... 以及其他各种需要的参数
     */

    public static void main(String[] args) throws Exception {
        //1.创建一个job
        Configuration configuration = new Configuration();

        //map端开启压缩
        configuration.set("mapreduce.map.output.compress","true");
        // 设置map端输出压缩方式
        configuration.set("mapreduce.map.output.compress.codec","org.apache.hadoop.io.compress.DefaultCodec");


        //map端开启压缩
        configuration.set("mapreduce.output.fileoutputformat.compress","true");
        // 设置map端输出压缩方式
        configuration.set("mapreduce.output.fileoutputformat.compress.codec","org.apache.hadoop.io.compress.DefaultCodec");


        //  通过Configuration对象获取Job对象，该job对象会组织所有的该MapReduce程序所有的各种组件
        Job job = Job.getInstance(configuration);

        //2.设置jar包的位置。找到job加载的类
        job.setJarByClass(MyWordCount2.class);

        //3.指定mapper and reduce的类
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);

        //4.指定mapper的类输出结果的类型，如果mapper and reduce的输出结果类相同，下面的两行代码可以省略
//        job.setMapOutputKeyClass(Text.class);
//        job.setMapOutputValueClass(IntWritable.class);

        //5.指定reduce的输出类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        //设置combine合并
       job.setCombinerClass(WordCountReducer.class);

        String path="D:/hadoop/wordcountoutput";
        File file=new File(path);
        if(file.exists()){

            File[] files = file.listFiles();
            for (File file1 : files) {
                file1.delete();
            }
            file.delete();
        }
        //6.设置输入输出的路径
        FileInputFormat.setInputPaths(job, new Path("D:/hadoop/story.txt"));
        FileOutputFormat.setOutputPath(job, new Path(path));

        //7.最后提交任务(verbose布尔值 决定要不要将运行进度信息输出给用户)
        boolean waitForCompletion = job.waitForCompletion(true);

    }

}
