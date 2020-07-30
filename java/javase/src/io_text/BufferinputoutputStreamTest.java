package io_text;

import java.io.BufferedInputStream;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 抽象类                                      节点流                                        缓冲流
 * InputStream             FileInputStream        BufferedInputStream
 * OutputStream            FileOutputStream       BufferedOutputStream         
 * Writer                   FileWiter             BufferedWriter
 * Reader                   FileReader            BufferedReader
 * @Description: <Function>
 * @author  karlieswift
 * @date 2020年4月20日
 * @version "13.0.1"
 */
public class BufferinputoutputStreamTest {
/**
 * 缓冲流
 * BufferedInputStream
 * BufferedOutputStream
 * BufferedWriter
 * BufferedReader
 * 提供了流的读取，写入的速度快
 * @throws IOException 
 * @Function @param args
 */
	public static void main(String[] args)   {
		
		//1-创建文件
		File file1=new File("test//1.jpg");
		File file2=new File("test//13.jpg");
		//2-创建流
		//创建节点流
		//3-创建缓冲流
		
		BufferedInputStream bis = null;
		BufferedOutputStream bos=null;
		try {
			FileInputStream fis=new FileInputStream(file1);
			FileOutputStream fos=new FileOutputStream(file2);
			bis = new BufferedInputStream(fis);
			bos = new BufferedOutputStream(fos);
			//复制细节，读取写入
			byte [] buffer=new byte[10];
			int len;
			while((len=bis.read(buffer))!=-1) {
			//	bos.write(buffer, 0, len);
				for(int i=0;i<len;i++) {
					bos.write(buffer[i]);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//关闭流,先关闭外层，后关闭里层
			try {
				if(bis!=null) {
					bis.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(bos!=null){
					bos.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//其实外层关闭时，内层也将关闭
//			fis.close();
//			fos.close();
		}
		

		
	}
	
	
	
	
	
}
