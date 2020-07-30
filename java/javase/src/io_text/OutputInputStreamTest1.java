package io_text;

import java.io.*;
/**
 * 抽象类                                      节点流                                        缓冲流
 * InputStream             FileInputStream        BufferedInputStream
 * OutputStream            FileOutputStream        BufferedOutputStream         
 * Writer                   FileWiter             BufferedWriter
 * Reader                   FileReader            BufferedReader
 * @Description: <Function>
 * @author  karlieswift
 * @date 2020年4月20日
 * @version "13.0.1"
 */
public class OutputInputStreamTest1 {

	
	public static void main(String[] args)  {
		String srcdir="test//1.mp4";
		String desdir="test//2.mp4";
		copyfile(srcdir, desdir);
	}
	//进行图片的写入,封装成一个函数，文件复制
	public static void copyfile(String srcdir,String desdir) {
		//进行图片的写入
				FileInputStream fis=null;
				FileOutputStream fos=null;
				try {
					File file1=new File(srcdir);
					File file2=new File(desdir);
					fis = new FileInputStream(file1);
					fos = new FileOutputStream(file2);
					int len;
					byte[]b=new byte[1024];
					while((len=fis.read(b))!=-1) {
					//	写入方式1
//					for(int i=0;i<len;i++) {
//						fos.write(b[i]);
//					//	System.out.print(b[i]);
//					}
						//写入方式2
						fos.write(b, 0, len);
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
							if(fos!=null) {
								fos.close();
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					try {
						if(fis!=null) {
							fis.close();
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	}
}
