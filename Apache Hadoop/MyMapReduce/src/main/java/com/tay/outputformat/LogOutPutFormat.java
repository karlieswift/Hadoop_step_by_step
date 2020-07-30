package com.tay.outputformat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author karlieswift
 * date: 2020/5/25 10:23
 * ClassName: LogOutPutForMat
 * @version java "13.0.1"
 */
public class LogOutPutFormat extends FileOutputFormat<Text, NullWritable> {
    @Override
    public RecordWriter<Text, NullWritable> getRecordWriter(TaskAttemptContext job) throws IOException, InterruptedException {
        MyOutputFormat myOutputFormat=new MyOutputFormat(job.getConfiguration());
        return myOutputFormat;
    }
}
 class MyOutputFormat extends  RecordWriter<Text,NullWritable>{

     private FSDataOutputStream fsDataOutputStream1;
     private FSDataOutputStream fsDataOutputStream2;

    private FileSystem fileSystem;
    public MyOutputFormat(Configuration configuration){

        try {
            FileSystem fileSystem = this.fileSystem.get(configuration);

            fsDataOutputStream1= fileSystem.create(new Path("D:/hadoop/log/1.txt"));
            fsDataOutputStream2=fileSystem.create(new Path("D:/hadoop/log/2.log"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
     @Override
     public void write(Text key, NullWritable value) throws IOException, InterruptedException {
        String str="kaggle";
         String s = key.toString();
         if(s.contains(str)){
             fsDataOutputStream1.writeBytes(s+"\n");
         }else {
             fsDataOutputStream2.writeBytes(s+"\n");
         }

     }

     @Override
     public void close(TaskAttemptContext context) throws IOException, InterruptedException {
         IOUtils.closeStream(fsDataOutputStream1);
         IOUtils.closeStream(fsDataOutputStream2);
     }
 }