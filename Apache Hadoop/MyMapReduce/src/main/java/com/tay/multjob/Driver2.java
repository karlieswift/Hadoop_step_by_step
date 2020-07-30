package com.tay.multjob;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.File;

/**
 * @author karlieswift
 * date: 2020/5/26 22:12
 * ClassName: Driver1
 * @version java "13.0.1"
 */
public class Driver2{
    public static void main(String[] args) throws Exception{
        //1.创建一个job
        Configuration configuration = new Configuration();
        //  通过Configuration对象获取Job对象，该job对象会组织所有的该MapReduce程序所有的各种组件
        Job job = Job.getInstance(configuration);

        //2.设置jar包的位置。找到job加载的类
        job.setJarByClass(Driver2.class);

        //3.指定mapper and reduce的类
        job.setMapperClass(Mapper2.class);
        job.setReducerClass(Reducer2.class);

        //4.指定mapper的类输出结果的类型，如果mapper and reduce的输出结果类相同，下面的两行代码可以省略
//        job.setMapOutputKeyClass(Text.class);
//        job.setMapOutputValueClass(IntWritable.class);

        //5.指定reduce的输出类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);



        String path="D:/hadoop/multresult2";
        File file=new File(path);
        if(file.exists()){

            File[] files = file.listFiles();
            for (File file1 : files) {
                file1.delete();
            }
            file.delete();
        }
        //6.设置输入输出的路径
        FileInputFormat.setInputPaths(job, new Path("D:/hadoop/multresult1/part-r-00000"));
        FileOutputFormat.setOutputPath(job, new Path(path));

        //7.最后提交任务(verbose布尔值 决定要不要将运行进度信息输出给用户)
        boolean waitForCompletion = job.waitForCompletion(true);

    }
}
 