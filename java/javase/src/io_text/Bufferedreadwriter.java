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

public class Bufferedreadwriter {
	public static void main(String[] args) throws IOException     {
		//buffferedRead->FileReader->File
		BufferedReader br=new BufferedReader(new FileReader(new File("test//taylor.txt")));
		BufferedWriter bw=new BufferedWriter(new FileWriter(new File("test//12.txt")));
		//读写操作方式1
//		int len;
//		char []ch=new char[10];
//		while((len=br.read(ch))!=-1) {
//			bw.write(ch,0,len);
//		}
		//读写操作方式2
		String data;
		while((data=br.readLine())!=null) {
		//	bw.write(data+"\n");
			bw.write(data);
			bw.newLine();
		}
		bw.close();
		br.close();
	}

}
