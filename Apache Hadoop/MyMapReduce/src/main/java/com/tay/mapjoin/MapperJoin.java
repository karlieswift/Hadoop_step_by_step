package com.tay.mapjoin;

import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;

/**
 * @author karlieswift
 * date: 2020/5/27 20:00
 * ClassName: MapperJoin
 * @version java "13.0.1"
 */
public class MapperJoin  extends Mapper<LongWritable, Text,Text, NullWritable> {


    HashMap<String, String> map=new HashMap<>();
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {

        URI[] cacheFiles = context.getCacheFiles();
        URI cacheFile = cacheFiles[0];
        FileSystem fileSystem = FileSystem.get(context.getConfiguration());
        FSDataInputStream open = fileSystem.open(new Path(cacheFile));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(open, "utf8"));
        String str="";
        while((str=bufferedReader.readLine())!=null){
            String[] split = str.split("\t");
            map.put(split[0],split[1]);
        }
        IOUtils.closeStream(bufferedReader);
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] split = value.toString().split("\t");
        context.write(new Text(split[0]+"\t"+map.get(split[1])+"\t"+split[2]),NullWritable.get());
    }
}
 