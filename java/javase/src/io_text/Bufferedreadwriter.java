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

public class Bufferedreadwriter {
	public static void main(String[] args) throws IOException     {
		//buffferedRead->FileReader->File
		BufferedReader br=new BufferedReader(new FileReader(new File("test//taylor.txt")));
		BufferedWriter bw=new BufferedWriter(new FileWriter(new File("test//12.txt")));
		//��д������ʽ1
//		int len;
//		char []ch=new char[10];
//		while((len=br.read(ch))!=-1) {
//			bw.write(ch,0,len);
//		}
		//��д������ʽ2
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
