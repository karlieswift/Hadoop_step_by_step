package ipadresstest;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPadressTest {

	public static void main(String[] args) throws IOException {
		InetAddress inet1 = InetAddress.getByName("localhost");
		System.out.println(inet1);// localhost/127.0.0.1
		InetAddress inet2 = InetAddress.getByName("www.baidu.com");
		System.out.println(inet2);// www.baidu.com/39.156.66.14
		InetAddress inet3 = InetAddress.getByName("127.0.0.1");
		System.out.println(inet3);/// 127.0.0.1
		// 获取本地ip
		InetAddress inet4 = InetAddress.getLocalHost();
		System.out.println(inet4);// DESKTOP-T02LCK9/192.168.122.1
		// getHostName()
		System.out.println(inet2.getHostName());// www.baidu.com
		// getHostAddress()
		System.out.println(inet2.getHostAddress());// 39.156.66.14
	}
}
