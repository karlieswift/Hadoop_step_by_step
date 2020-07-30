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
public class InputStreamReaderTest {
public static void main(String[] args) throws IOException {
	InputStreamReader isr=new InputStreamReader(System.in);
	BufferedReader br=new BufferedReader(isr);
	String data;
	while(true) {
		data=br.readLine();
		if("e".equalsIgnoreCase(data) || "Exit".equalsIgnoreCase(data)) {
			System.out.println("�˳�");
			break;
		}
		else{
			System.out.println(data);
		}
	}
}
}
