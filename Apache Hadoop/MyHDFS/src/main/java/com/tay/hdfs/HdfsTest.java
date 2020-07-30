package com.tay.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.Arrays;

/**
 * @author karlieswift
 * date: 2020/5/18 17:01
 * ClassName: HdfsTest
 * @version java "13.0.1"
 */
public class HdfsTest {

    private URI uri=URI.create("hdfs://hadoop2:9820");
    private Configuration configuration =new Configuration();
    private String user="tay";
    private FileSystem fileSystem=null;
    @Before  //excute before @Test
    public void init_filesystem() throws IOException, InterruptedException {
        configuration.set("dfs.replication", "2");
       fileSystem= FileSystem.get(uri,configuration,user);
    }
    @After  // excute after @Test
    public void close_io() throws IOException {
        fileSystem.close();
    }
    /**
     * uplaod files
     */
    @Test
    public void uploadTest() throws IOException {

         /**
         *parameter1:true=move false=copy
         * parameter2: origin directory
         * parameter3:destination directory
         */
        fileSystem.copyFromLocalFile(false,new Path("D:/hadoop/a.txt"),new Path("/java/"));
    //    fileSystem.copyFromLocalFile(true ,new Path("D:/hadoop/a.txt"),new Path("/java/"));
    }


    /**
     *dwonload file
     * last parameter =true :have not a crc'file
     */
    @Test
    public void download() throws IOException {
        fileSystem.copyToLocalFile(false,new Path("/java/a.txt"),new Path("D:/hadoop/"),true);
    }


    @Test
    public void delfile() throws IOException {
        //z注意这里把file文件夹一块删除
        fileSystem.delete(new Path("/java/file/"),true);

    }

    @Test
    public  void mkdirTest() throws IOException {
        boolean mkdirs = fileSystem.mkdirs(new Path("/java/file/file1"));
        if(mkdirs==true){
            System.out.println("succceed");
        }else
            System.out.println("failed");
    }


    @Test
    public void renameTest() throws IOException {
        //文件的rename与移动
      //  boolean rename = fileSystem.rename(new Path("/java/file"), new Path("/java/test"));
       fileSystem.rename(new Path("/java/test/file1"), new Path("/java/"));
    }

    /**
     * 递归打印文件方法一：
     * @throws IOException
     */
    @Test
    public void filedetails() throws IOException {
        RemoteIterator<LocatedFileStatus> it = fileSystem.listFiles(new Path("/user/"), true);
        while(it.hasNext()){
            LocatedFileStatus next = it.next();


            //获取文件的块信息
            BlockLocation[] blockLocations = next.getBlockLocations();
            String[] hosts = null;
            for(BlockLocation block:blockLocations){
                hosts= block.getHosts();
            }
            System.out.printf( "%-20s","文件名字:"+next.getPath().getName());
            System.out.printf( "%-40s","文件路径:"+next.getPath().toString().split("/",4)[3]);
            System.out.printf( "%-20s","文件长度:"+next.getLen());
            System.out.printf( "%-20s","组:"+next.getGroup()) ;
            System.out.printf( "%-10s","用户:"+next.getOwner());
            System.out.printf( "%-10s","服务器:"+ Arrays.toString(hosts));
            System.out.println();

            /*
            文件名字:a              文件路径:user/input/a                       文件长度:11             组:supergroup        用户:tay    用户:[hadoop3, hadoop2, hadoop4]
            文件名字:wc.input       文件路径:user/input/wc.input                文件长度:49             组:supergroup        用户:tay    用户:[hadoop3, hadoop2, hadoop4]
            文件名字:jdk            文件路径:user/jdk                           文件长度:195228853      组:supergroup        用户:tay    用户:[hadoop3, hadoop4]
            文件名字:a.tc           文件路径:user/tay/a.tc                      文件长度:11             组:supergroup        用户:tay    用户:[hadoop3, hadoop2, hadoop4]
            文件名字:a              文件路径:user/tay/input/a                   文件长度:11             组:supergroup        用户:tay    用户:[hadoop3, hadoop2, hadoop4]
            文件名字:wc.input       文件路径:user/tay/input/wc.input            文件长度:49             组:supergroup        用户:tay    用户:[hadoop3, hadoop2, hadoop4]
            文件名字:_SUCCESS       文件路径:user/tay/output1/_SUCCESS          文件长度:0              组:supergroup        用户:tay    用户:null
            文件名字:part-r-00000   文件路径:user/tay/output1/part-r-00000      文件长度:47             组:supergroup        用户:tay    用户:[hadoop3, hadoop2, hadoop4]
            文件名字:_SUCCESS       文件路径:user/tay/output2/_SUCCESS          文件长度:0              组:supergroup        用户:tay    用户:null
            文件名字:part-r-00000   文件路径:user/tay/output2/part-r-00000      文件长度:47             组:supergroup        用户:tay    用户:[hadoop3, hadoop2, hadoop4]


             */
        }
    }




    /**
     *
     * 递归打印文件方法二：
     * @throws IOException
     */
 @Test
    public void StatusTest() throws IOException {
     FileStatus[] fileStatuses = fileSystem.listStatus(new Path("/user/"));
     for(FileStatus f:fileStatuses){
        printfile(f.getPath(),fileSystem);
     }

     /*
     user/input/a
     user/input/wc.input
     user/jdk
     user/tay/a.tc
     user/tay/input/a
     user/tay/input/wc.input
     user/tay/output1/_SUCCESS
     user/tay/output1/part-r-00000
     user/tay/output2/_SUCCESS
     user/tay/output2/part-r-00000
 */
 }
    public void printfile(Path path,FileSystem fileSystem) throws IOException {
        FileStatus[] fileStatuses = fileSystem.listStatus(path);
        for (FileStatus f : fileStatuses) {
            if (f.isFile()) {  //判断当前状态是否为文件
                System.out.println(f.getPath().toString().split("/", 4)[3]);
            } else
                printfile(f.getPath(), fileSystem);
        }
    }




    /**
     * 文件上传和下载的本质:
     * IO流的操作
     * 文件上传:
     * 通过指向本地文件的输入流，进行数据的读取， 然后将读取到的数据，通过指向HDFS文件的输出流，将数据写出.
     * 文件下载:
     * 通过指向HDFS文件的输入流，进行数据的读取， 然后将读取到的数据，通过指定本地文件的输出流，将数据写出.
     */
    @Test
    public void upload_data() throws IOException { //上传文件
        //输入流
        FileInputStream fis=new FileInputStream(new File("D:/hadoop/pic.jpg"));
        //输出流
        FSDataOutputStream fos=fileSystem.create(new Path("/user/pic.jpg"));
        //方法1
//        byte[] buffer=new byte[1024];
//        int len;
//        while((len=fis.read(buffer))!=-1){
//            fos.write(buffer,0,len);
//        }
//        fos.close();
//        fis.close();
        //方法2
        IOUtils.copyBytes(fis,fos,configuration);
        IOUtils.closeStream(fos);
        IOUtils.closeStream(fis);
    }

    @Test
    public void download_data()  throws  IOException{
        //输出流
        FileOutputStream fos=new FileOutputStream(new File("D:/pic.jpg"));
        //输入流
        FSDataInputStream fis=fileSystem.open(new Path("/user/pic.jpg"));
        //方法1
//        byte []buffer=new byte[1024];
//        int len;
//        while((len=fis.read(buffer))!=-1){
//            fos.write(buffer,0,len);
//        }
//
//        fis.close();
//        fos.close();
        //方法2
          IOUtils.copyBytes(fis,fos,configuration);
          IOUtils.closeStream(fis);
          IOUtils.closeStream(fos);
    }
}

