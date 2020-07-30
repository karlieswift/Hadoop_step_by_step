package io_text;

import java.io.File;
import java.io.IOException;

public class FileRTest2 {

	public static void main(String[] args) throws IOException {

		File file1 = new File("D:\\Project\\java\\javase\\test\\hello.txt");
		File file2 = new File("test\\karlie.txt");
		File file3 = new File("test\\hello.txt");
		// System.out.println(file3.length());
		// 把file1 rename为file2,file1必须存在，file2不能存在
//		boolean renameto=file1.renameTo(file2);
//		System.out.println(renameto);
		
		
        //存在的文件
//		System.out.println(file1.exists());
//		System.out.println(file1.isFile());
//		System.out.println(file1.canRead());
//		System.out.println(file1.canWrite());
//		System.out.println(file1.isHidden());
		System.out.println("-----------------------------------");
		//不存在的文件
//		System.out.println(file2.exists());
//		System.out.println(file2.isFile());
//		System.out.println(file2.canRead());
//		System.out.println(file2.canWrite());
//		System.out.println(file2.isHidden());
		
		
		
	//创建文件，删除文件
		File file4=new File("test//taylor.txt");
//		if(!file4.exists()) {
//			file4.createNewFile();
//			System.out.println("创建成功");
//		}else {
//			file4.delete();
//			System.out.println("删除成功");
//		}
	//创建目录。删除目录,mkdirs可以创建多级目录
		File file5=new File("test//taylordir1//ee");
		if(!file5.exists()) {
			if(file5.mkdirs()) {
				System.out.println("创建成功1");
			}
		}
		File file6=new File("test//taylordir");
		if(!file6.exists()) {
			if(file6.mkdir()) {
				System.out.println("创建成功2");
			}
		}
		//删除文件夹
	//	file5.delete();

	}
}
