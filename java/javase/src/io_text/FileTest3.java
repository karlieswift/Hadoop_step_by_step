package io_text;

import java.io.File;
/**
 * 
 * @Description: �ݹ�����ļ�
 * @author  karlieswift
 * @date 2020��4��9��
 * @version "13.0.1"
 * 	//��ʾ���ļ�Ŀ¼�µ������ļ�
 *	//���Ҹ��ļ�Ŀ¼�µ�����ָ���ļ���ʽ�ļ�
 */
public class FileTest3 {

	public static void main(String[] args) {
		
		File file=new File("test//");
		System.out.println("��ʾ���ļ�Ŀ¼�µ������ļ�");
		show(file);
		System.out.println("����jpg�ļ�");
		String str="jpg";
		search(file, str);
		
	}
	
	
	
	//��ʾ���ļ�Ŀ¼�µ������ļ�
	public static void show(File file) {
		File []filelist=file.listFiles();
		for(File f:filelist) {
			if(f.isDirectory()) {
				show(f);
			}else {
				System.out.println(f.getAbsolutePath());
			}
		}
	}
	
	
	//���Ҹ��ļ�Ŀ¼�µ�����ָ���ļ���ʽ�ļ�
	public static void search(File file,String str) {
		File []filelist=file.listFiles();
		for(File f:filelist) {
			if(f.isDirectory()) {
				search(f,str);
			}else {
				String []namelist=f.getName().split("\\.");
				if(namelist[namelist.length-1].equals(str)) {
					System.out.println(f.getAbsolutePath());
				}
			}
		}
	}
	
	
}
