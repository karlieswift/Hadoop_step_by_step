package io_text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * �ֶ�д�Լ���scanner��
 * 
 * @Description: <Function>
 * @author karlieswift
 * @date 2020��4��6��
 * @version "13.0.1"
 */
public class OverreadScanner {
	public static void main(String[] args) {
		MyScanner scan = new MyScanner();
		String str = scan.nextString();
		System.out.println(str);
		int number = scan.nextInt();
		System.out.println(number);
		double d = scan.nextDouble();
		System.out.println(d);
		byte b = scan.nextByte();
		System.out.println(b);

	}
}

class MyScanner {
	/**
	 * �����ַ���
	 * 
	 * @Function @return
	 */
	public static String nextString() {
		String str = null;
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		try {
			str = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * ����int
	 * 
	 * @Function @return
	 */
	public static int nextInt() {
		String str = nextString();
		return Integer.parseInt(str);
	}

	/**
	 * ����double
	 * 
	 * @Function @return
	 */
	public static double nextDouble() {
		String str = nextString();
		return Double.parseDouble(str);
	}

	/**
	 * ����Byte
	 * 
	 * @Function @return
	 */
	public static Byte nextByte() {
		String str = nextString();
		return Byte.parseByte(str);
	}

}