package io_text;

import java.io.File;
import java.util.Date;

public class FIleTest {
	public static void main(String[] args) {
		 //������
		File file1=new File("C:\\Users\\karlieswift\\Desktop\\txt.txt");		
		File file2=new File("C:\\Users\\karlieswift\\Desktop\\","fillllll");
		//C:\\Users\\karlieswift\\Desktop\\
		File file3=new File(file2,"hi.txt");
		File file4=new File("C:\\Users\\karlieswift\\Desktop\\");
//		
		System.out.println(file1.getAbsolutePath());//����·��
		System.out.println(file1.getPath());//����·��
		System.out.println(file1.getParent());//�ϲ�·��
		System.out.println(file1.getName());//�ļ�����
		System.out.println(file1.length());//����
		System.out.println(new Date(file1.lastModified()));//�ϴ��޸�ʱ��
//		
		//�ļ�Ŀ¼��ʽ1���ļ�Ŀ¼�������
//		String [] list=file4.list();
//		for( String s:list) {	
//			System.out.println(s);
//		}	
		//�ļ�Ŀ¼��ʽ2,ͬʱҲ�����·��
		File []listfile=file4.listFiles();
		for( File s:listfile) {
			System.out.println(s);
		}
	
		
		
	}

}
