package com.tay.compress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.apache.hadoop.io.compress.CompressionInputStream;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.util.ReflectionUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @author karlieswift
 * date: 2020/5/28 10:53
 * ClassName: TestCompress
 * @version java "13.0.1"
 */
public class TestCompress {


    //压缩
    @Test
    public void compress() throws Exception {

        String codeways = "org.apache.hadoop.io.compress.DefaultCodec";


        Configuration configuration = new Configuration();
        FileInputStream fileInputStream = new FileInputStream(new File("D:/hadoop/story.txt"));

        Class<?> aClass = Class.forName(codeways);
        CompressionCodec compressionCodec = (CompressionCodec) ReflectionUtils.newInstance(aClass, configuration);

        FileOutputStream fileOutputStream = new FileOutputStream(new File("D:/hadoop/story" + compressionCodec.getDefaultExtension()));

        CompressionOutputStream outputStream = compressionCodec.createOutputStream(fileOutputStream);
        IOUtils.copyBytes(fileInputStream, outputStream, configuration);
        IOUtils.closeStream(fileInputStream);
        IOUtils.closeStream(fileInputStream);


    }

    //解压
    @Test
    public void uncompress()throws Exception {


        Configuration configuration = new Configuration();

        FileInputStream fileInputStream = new FileInputStream(new File("D:/hadoop/story.deflate"));
        FileOutputStream fileOutputStream = new FileOutputStream(new File("D:/hadoop/story1.txt"));

        CompressionCodec codec = new CompressionCodecFactory(configuration).getCodec(new Path("D:/hadoop/story.deflate"));
        CompressionInputStream inputStream = codec.createInputStream(fileInputStream);
        IOUtils.copyBytes(inputStream,fileOutputStream,configuration);
        IOUtils.closeStream(fileOutputStream);
        IOUtils.closeStream(fileInputStream);



    }

}