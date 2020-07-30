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
public class OutputInputStreamTest1 {

	
	public static void main(String[] args)  {
		String srcdir="test//1.mp4";
		String desdir="test//2.mp4";
		copyfile(srcdir, desdir);
	}
	//����ͼƬ��д��,��װ��һ���������ļ�����
	public static void copyfile(String srcdir,String desdir) {
		//����ͼƬ��д��
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
					//	д�뷽ʽ1
//					for(int i=0;i<len;i++) {
//						fos.write(b[i]);
//					//	System.out.print(b[i]);
//					}
						//д�뷽ʽ2
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
