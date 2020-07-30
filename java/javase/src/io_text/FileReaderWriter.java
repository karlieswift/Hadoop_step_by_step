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
 * ������                                      �ڵ���                                        ������
 * InputStream             FileInputStream        BufferedInputStream
 * OutputStream            FileOutputStream        BufferedOutputStream         
 * Writer                   FileWiter             BufferedWriter
 * Reader                   FileReader            BufferedReader

 * 
 * 
 * @Description: <Function>
 * @author  karlieswift
 * @date 2020��4��6��
 * @version "13.0.1"
 */
public class FileReaderWriter {

	public static void main(String[] args) throws IOException  {
		File file=new File("test//taylor.txt");
		//��taylor.txt����
		FileReader fr=new FileReader(file);
		//���ض�����ַ������������󣬷���-1
		//��ʽ1
//		int data=fr.read();
//		while(data!=-1) {
//			System.out.print((char)data);
//			data=fr.read();//next
//		}
		//��ʽ2
		int data;
		
		while((data=fr.read())!=-1) {
			System.out.println((char)data);
		}	
		//���Ĺر�
		fr.close();
		
		
		
		
		
	
	 
	
	}
}




































