package com.tay.commomfans;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.File;
import java.net.URI;

/**
 * 查看共同的粉丝
 * @author karlieswift
 * date: 2020/5/27 17:40
 * ClassName: FansDriver
 * @version java "13.0.1"
 *
 *
 *
 * datas:
A:B,C,D,F,E,O
B:A,C,E,K
C:F,A,D,I
D:A,E,F,L
E:B,C,D,M,L
F:A,B,C,D,E,O,M
G:A,C,D,E,F
H:A,C,D,E,O
I:A,O
J:B,O
K:A,C,D
L:D,E,F
M:E,F,G
O:A,H,I,J
 */
public class FansDriver {

    public static void main(String[] args) throws Exception{
        Configuration conf = new Configuration();
        Job job  = Job.getInstance(conf);

        job.addCacheFile(new URI("file:///D:/hadoop/join/pd.txt"));

        job.setJarByClass(FansDriver.class);
        job.setMapperClass(FansMapper.class);
        job.setReducerClass(FansReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);



        String path="D:/hadoop/output";
        File file=new File(path);
        if(file.exists()){

            File[] files = file.listFiles();
            for (File file1 : files) {
                file1.delete();
            }
            file.delete();
        }

        FileInputFormat.setInputPaths(job,new Path("D:/hadoop/commonfans"));
        FileOutputFormat.setOutputPath(job,new Path(path));

        job.waitForCompletion(true);
    }
}
 