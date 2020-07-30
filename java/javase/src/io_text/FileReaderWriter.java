package io_text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;

/**
 * 
 * 
 * 抽象类                                      节点流                                        缓冲流
 * InputStream             FileInputStream        BufferedInputStream
 * OutputStream            FileOutputStream        BufferedOutputStream         
 * Writer                   FileWiter             BufferedWriter
 * Reader                   FileReader            BufferedReader

 * 
 * 
 * @Description: <Function>
 * @author  karlieswift
 * @date 2020年4月6日
 * @version "13.0.1"
 */
public class FileReaderWriter {

	public static void main(String[] args) throws IOException  {
		File file=new File("test//taylor.txt");
		//将taylor.txt读出
		FileReader fr=new FileReader(file);
		//返回读入的字符，如果到达最后，返回-1
		//方式1
//		int data=fr.read();
//		while(data!=-1) {
//			System.out.print((char)data);
//			data=fr.read();//next
//		}
		//方式2
		int data;
		
		while((data=fr.read())!=-1) {
			System.out.println((char)data);
		}	
		//流的关闭
		fr.close();
		
		
		
		
		
	
	 
	
	}
}




































