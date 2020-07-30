package io_text;

import java.io.File;
/**
 * 
 * @Description: 递归查找文件
 * @author  karlieswift
 * @date 2020年4月9日
 * @version "13.0.1"
 * 	//显示该文件目录下的所有文件
 *	//查找该文件目录下的所有指定文件格式文件
 */
public class FileTest3 {

	public static void main(String[] args) {
		
		File file=new File("test//");
		System.out.println("显示该文件目录下的所有文件");
		show(file);
		System.out.println("查找jpg文件");
		String str="jpg";
		search(file, str);
		
	}
	
	
	
	//显示该文件目录下的所有文件
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
	
	
	//查找该文件目录下的所有指定文件格式文件
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
