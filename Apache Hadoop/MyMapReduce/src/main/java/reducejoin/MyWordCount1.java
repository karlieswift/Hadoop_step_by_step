package reducejoin;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 *
 *
 * IDEA 传入参数
 * MyWordCount1 主要测试的是本地统计文本文件的单词出现频率
 * @author karlieswift
 * date: 2020/5/20 19:27
 * ClassName: MyWordCount
 * @version java "13.0.1"
 */
public class MyWordCount1{
    /**
     * 该main方法是该mapreduce程序运行的入口，其中用一个Job类对象来管理程序运行时所需要的很多参数：
     * 比如，指定用哪个组件作为数据读取器、数据结果输出器 指定用哪个类作为map阶段的业务逻辑类，哪个类作为reduce阶段的业务逻辑类
     * 指定wordcount job程序的jar包所在路径 .... 以及其他各种需要的参数
     */

    public static void main(String[] args) throws Exception {
        //1.创建一个job
        Configuration configuration = new Configuration();
        //设置HDFS NameNode的地址
        configuration.set("fs.defaultFS", "hdfs://hadoop2:9820");
        // 指定MapReduce运行在Yarn上
        configuration.set("mapreduce.framework.name","yarn");
        // 指定mapreduce可以在远程集群运行
        configuration.set("mapreduce.app-submission.cross-platform","true");
        //指定Yarn resourcemanager的位置
        configuration.set("yarn.resourcemanager.hostname","hadoop3");




        //  通过Configuration对象获取Job对象，该job对象会组织所有的该MapReduce程序所有的各种组件
        Job job = Job.getInstance(configuration);

        //2.设置jar包的位置。找到job加载的类
      //  job.setJarByClass(MyWordCount1.class);

       job.setJar("D:\\Project\\BigData\\MyMapReduce\\target\\MyMapReduce-1.0-SNAPSHOT.jar");



        //3.指定mapper and reduce的类
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);

        //4.指定mapper的类输出结果的类型，如果mapper and reduce的输出结果类相同，下面的两行代码可以省略
//        job.setMapOutputKeyClass(Text.class);
//        job.setMapOutputValueClass(IntWritable.class);

        //5.指定reduce的输出类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        //6.设置输入输出的路径
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
//        FileInputFormat.setInputPaths(job, new Path("hdfs://hadoop2:9820/user/tay/input"));
//        FileOutputFormat.setOutputPath(job, new Path("hdfs://hadoop2:9820/user/tay/output"));

        //7.最后提交任务(verbose布尔值 决定要不要将运行进度信息输出给用户)
        boolean waitForCompletion = job.waitForCompletion(true);

    }

}
