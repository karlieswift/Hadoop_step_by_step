package io_text;

import java.io.File;
import java.util.Date;

public class FIleTest {
	public static void main(String[] args) {
		 //构造器
		File file1=new File("C:\\Users\\karlieswift\\Desktop\\txt.txt");		
		File file2=new File("C:\\Users\\karlieswift\\Desktop\\","fillllll");
		//C:\\Users\\karlieswift\\Desktop\\
		File file3=new File(file2,"hi.txt");
		File file4=new File("C:\\Users\\karlieswift\\Desktop\\");
//		
		System.out.println(file1.getAbsolutePath());//绝对路径
		System.out.println(file1.getPath());//绝对路径
		System.out.println(file1.getParent());//上层路径
		System.out.println(file1.getName());//文件名字
		System.out.println(file1.length());//长度
		System.out.println(new Date(file1.lastModified()));//上次修改时间
//		
		//文件目录方式1，文件目录必须存在
//		String [] list=file4.list();
//		for( String s:list) {	
//			System.out.println(s);
//		}	
		//文件目录方式2,同时也会输出路径
		File []listfile=file4.listFiles();
		for( File s:listfile) {
			System.out.println(s);
		}
	
		
		
	}

}
