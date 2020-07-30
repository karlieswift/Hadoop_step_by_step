package tcp1;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		server();
	}

	public static void server() {
		ServerSocket serverSocket = null;
		Socket socket = null;
		InputStream is = null;
		try {

			// 1.创建服务器端的ServerSocket，指明自己的端口号
			serverSocket = new ServerSocket(8999);
			// 2.调用accept()表示接收来自于客户端的socket
			socket = serverSocket.accept();
			is = socket.getInputStream();
			int len;
			byte[] b = new byte[2];

//			//方法一有乱码
//			while((len=is.read(b))!=-1) {
//				String s=new String(b,0,len);
//				System.out.print(s);
//			}

			// 方法二
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			while ((len = is.read(b)) != -1) {
				bos.write(b, 0, len);
			}
			System.out.println(bos.toString());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
				if (socket != null) {
					socket.close();
				}
				if (serverSocket != null) {
					serverSocket.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
