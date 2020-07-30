package io_text;

import java.io.*;

/**
 * ������                                      �ڵ���                                        ������
 * InputStream             FileInputStream        BufferedInputStream
 * OutputStream            FileOutputStream        BufferedOutputStream         
 * Writer                   FileWiter             BufferedWriter
 * Reader                   FileReader            BufferedReader
 * @Description: <Function>
 * @author  karlieswift
 * @date 2020��4��20��
 * @version "13.0.1"
 */
public class FilereaderWriter1 {

	public static void main(String[] args)  {
	
		
		
		//д�뵽�ļ�
		File file1=new File("test//hello1.txt");
		//�ṩFilewriter�Ķ����������ݵ�д��,true������ �� Ĭ��false����
		FileWriter fw = null;
		try {
			fw = new FileWriter(file1,true);
			//FileWriter fw=new FileWriter(file1);
			//д���ľ������
			fw.write("I have a dream\n");
			fw.write("I have another dream\n".toCharArray());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			//���ر�
			try {
				if(fw!=null) {
					fw.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		//д���ļ�����
		FileReader fr=null;
		try {
			File file=new File("test//hello1.txt");
			fr = new FileReader(file);
			//�������
			char []ch=new char[6];
			int len;
			while((len=fr.read(ch))!=-1) {
				for(int i=0;i<len;i++) {
					System.out.print(ch[i]);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
				try {
					if(fr!=null) {
						fr.close();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 
		}
			
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
