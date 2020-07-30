package reducejoin;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * MyWordCount
 * @author karlieswift
 * date: 2020/5/20 19:27
 * ClassName: MyWordCount
 * @version java "13.0.1"
 */
public class MyWordCount {
    /**
     * 该main方法是该mapreduce程序运行的入口，其中用一个Job类对象来管理程序运行时所需要的很多参数：
     * 比如，指定用哪个组件作为数据读取器、数据结果输出器 指定用哪个类作为map阶段的业务逻辑类，哪个类作为reduce阶段的业务逻辑类
     * 指定wordcount job程序的jar包所在路径 .... 以及其他各种需要的参数
     */

    public static void main(String[] args) throws Exception {
        //1.创建一个job
        Configuration configuration = new Configuration();
        //  通过Configuration对象获取Job对象，该job对象会组织所有的该MapReduce程序所有的各种组件
        Job job = Job.getInstance(configuration);

        //2.设置jar包的位置。找到job加载的类
        job.setJarByClass(MyWordCount.class);

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

        //7.最后提交任务(verbose布尔值 决定要不要将运行进度信息输出给用户)
        boolean waitForCompletion = job.waitForCompletion(true);

    }

}

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

        System.out.println("000000000000000000000000000000");
        int sum = 0;
        for (IntWritable value : values) {

            sum += value.get();
        }
        result.set(sum);
        System.out.println(key.toString()+" "+sum);
        context.write(key, result);
    }
}



















