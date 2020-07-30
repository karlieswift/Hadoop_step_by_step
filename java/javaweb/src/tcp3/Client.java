package tcp3;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 给客户反馈
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
			System.out.println("传输......................................");
			// 关闭数据的输出
			socket.shutdownOutput();
			// 获取反馈
			InputStream is = socket.getInputStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer1 = new byte[2];
			int len1;
			while ((len1 = is.read(buffer1)) != -1) {
				String str = new String(buffer1, 0, len1);
				System.out.print(str);
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
