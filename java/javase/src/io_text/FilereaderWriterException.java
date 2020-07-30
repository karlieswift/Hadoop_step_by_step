package io_text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

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
 * @Description: ��Ҫ�����쳣����
 * @author  karlieswift
 * @date 2020��4��6��
 * @version "13.0.1"
 */
public class FilereaderWriterException {

	public static void main(String[] args) {
		FileReader fr = null;
		try {
			File file = new File("test//taylor.txt");
			// ��taylor.txt����
			fr = new FileReader(file);
			// ���ض�����ַ������������󣬷���-1
			// ��ʽ1
//		int data=fr.read();
//		while(data!=-1) {
//			System.out.print((char)data);
//			data=fr.read();//next
//		}
			// ��ʽ2
			int data;
			while ((data = fr.read()) != -1) {
				System.out.print((char) data);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// ���Ĺر�
			try {
				if (fr != null)
					fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// FileOutputStream =new FileOutputStream(file)

	}
}
