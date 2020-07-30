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
public class FilereaderWriter1 {

	public static void main(String[] args)  {
	
		
		
		//写入到文件
		File file1=new File("test//hello1.txt");
		//提供Filewriter的对象。用于数据的写出,true不覆盖 ， 默认false覆盖
		FileWriter fw = null;
		try {
			fw = new FileWriter(file1,true);
			//FileWriter fw=new FileWriter(file1);
			//写出的具体操作
			fw.write("I have a dream\n");
			fw.write("I have another dream\n".toCharArray());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			//流关闭
			try {
				if(fw!=null) {
					fw.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		//写出文件内容
		FileReader fr=null;
		try {
			File file=new File("test//hello1.txt");
			fr = new FileReader(file);
			//读入操作
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
