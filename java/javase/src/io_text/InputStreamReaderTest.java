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
public class InputStreamReaderTest {
public static void main(String[] args) throws IOException {
	InputStreamReader isr=new InputStreamReader(System.in);
	BufferedReader br=new BufferedReader(isr);
	String data;
	while(true) {
		data=br.readLine();
		if("e".equalsIgnoreCase(data) || "Exit".equalsIgnoreCase(data)) {
			System.out.println("退出");
			break;
		}
		else{
			System.out.println(data);
		}
	}
}
}
