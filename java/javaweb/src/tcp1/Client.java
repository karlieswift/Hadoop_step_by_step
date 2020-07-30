package tcp1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 传输文字
 * 
 * @Description: <Function>
 * @author karlieswift
 * @date 2020年4月12日
 * @version "13.0.1"
 */
public class Client {
	public static void main(String[] args) {

		client();
	}

	public static void client() {
		OutputStream os = null;
		Socket socket = null;
		BufferedInputStream bis = null;
		// 获取
		try {
			bis = new BufferedInputStream(new FileInputStream(new File("test//hello1.txt")));
			// InetAddress inetAddress=InetAddress.getByName("192.168.2.167");//给我的手机发送成功
			InetAddress inetAddress = InetAddress.getByName("localhost");
			socket = new Socket(inetAddress, 8999);
			// 获取输出流
			os = socket.getOutputStream();
			int len;
			byte[] buffer = new byte[10];
			while ((len = bis.read(buffer)) != -1) {
				os.write(buffer, 0, len);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { // 关闭资源
			try {
				if (os != null) {
					os.close();
				}
				if (bis != null) {
					bis.close();
				}
				if (socket != null) {
					socket.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
