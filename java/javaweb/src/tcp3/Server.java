package tcp3;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws InterruptedException {
		server();
	}

	public static void server() throws InterruptedException {
		ServerSocket serverSocket = null;
		Socket socket = null;
		InputStream is = null;
		try {
			// 1.�����������˵�ServerSocket��ָ���Լ��Ķ˿ں�
			serverSocket = new ServerSocket(8999);
			// 2.����accept()��ʾ���������ڿͻ��˵�socket
			socket = serverSocket.accept();
			is = socket.getInputStream();
			int len;
			byte[] b = new byte[2];
//			//����һ������
//			while((len=is.read(b))!=-1) {
//				String s=new String(b,0,len);
//				System.out.print(s);
//			}
			// ������
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			while ((len = is.read(b)) != -1) {
				bos.write(b, 0, len);
			}
			System.out.println(bos.toString());// �ͻ��˵�����
			Thread.sleep(2000);// ��װ����
			// ���͸��ͻ���
			OutputStream os = socket.getOutputStream();
			os.write("�������".getBytes());
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
