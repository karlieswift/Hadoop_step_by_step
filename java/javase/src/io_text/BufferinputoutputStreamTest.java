package io_text;

import java.io.BufferedInputStream;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * ������                                      �ڵ���                                        ������
 * InputStream             FileInputStream        BufferedInputStream
 * OutputStream            FileOutputStream       BufferedOutputStream         
 * Writer                   FileWiter             BufferedWriter
 * Reader                   FileReader            BufferedReader
 * @Description: <Function>
 * @author  karlieswift
 * @date 2020��4��20��
 * @version "13.0.1"
 */
public class BufferinputoutputStreamTest {
/**
 * ������
 * BufferedInputStream
 * BufferedOutputStream
 * BufferedWriter
 * BufferedReader
 * �ṩ�����Ķ�ȡ��д����ٶȿ�
 * @throws IOException 
 * @Function @param args
 */
	public static void main(String[] args)   {
		
		//1-�����ļ�
		File file1=new File("test//1.jpg");
		File file2=new File("test//13.jpg");
		//2-������
		//�����ڵ���
		//3-����������
		
		BufferedInputStream bis = null;
		BufferedOutputStream bos=null;
		try {
			FileInputStream fis=new FileInputStream(file1);
			FileOutputStream fos=new FileOutputStream(file2);
			bis = new BufferedInputStream(fis);
			bos = new BufferedOutputStream(fos);
			//����ϸ�ڣ���ȡд��
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
			//�ر���,�ȹر���㣬��ر����
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
			//��ʵ���ر�ʱ���ڲ�Ҳ���ر�
//			fis.close();
//			fos.close();
		}
		

		
	}
	
	
	
	
	
}
