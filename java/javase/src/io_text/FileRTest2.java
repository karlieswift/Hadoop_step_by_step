package io_text;

import java.io.File;
import java.io.IOException;

public class FileRTest2 {

	public static void main(String[] args) throws IOException {

		File file1 = new File("D:\\Project\\java\\javase\\test\\hello.txt");
		File file2 = new File("test\\karlie.txt");
		File file3 = new File("test\\hello.txt");
		// System.out.println(file3.length());
		// ��file1 renameΪfile2,file1������ڣ�file2���ܴ���
//		boolean renameto=file1.renameTo(file2);
//		System.out.println(renameto);
		
		
        //���ڵ��ļ�
//		System.out.println(file1.exists());
//		System.out.println(file1.isFile());
//		System.out.println(file1.canRead());
//		System.out.println(file1.canWrite());
//		System.out.println(file1.isHidden());
		System.out.println("-----------------------------------");
		//�����ڵ��ļ�
//		System.out.println(file2.exists());
//		System.out.println(file2.isFile());
//		System.out.println(file2.canRead());
//		System.out.println(file2.canWrite());
//		System.out.println(file2.isHidden());
		
		
		
	//�����ļ���ɾ���ļ�
		File file4=new File("test//taylor.txt");
//		if(!file4.exists()) {
//			file4.createNewFile();
//			System.out.println("�����ɹ�");
//		}else {
//			file4.delete();
//			System.out.println("ɾ���ɹ�");
//		}
	//����Ŀ¼��ɾ��Ŀ¼,mkdirs���Դ����༶Ŀ¼
		File file5=new File("test//taylordir1//ee");
		if(!file5.exists()) {
			if(file5.mkdirs()) {
				System.out.println("�����ɹ�1");
			}
		}
		File file6=new File("test//taylordir");
		if(!file6.exists()) {
			if(file6.mkdir()) {
				System.out.println("�����ɹ�2");
			}
		}
		//ɾ���ļ���
	//	file5.delete();

	}
}
