package tcp2;

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
 * ����ͼƬ
 * @Description: <Function>
 * @author  karlieswift
 * @date 2020��4��12��
 * @version "13.0.1"
 */
public class Client {
	public static void main(String[] args) {
	
		client();
	}

	public static void client() {
		OutputStream os=null;
		Socket socket=null;
		BufferedInputStream bis=null;
		//��ȡ
		try {
			bis=new BufferedInputStream(new FileInputStream(new File("test//1.jpg")));
				//InetAddress inetAddress=InetAddress.getByName("192.168.2.167");//���ҵ��ֻ����ͳɹ�
				InetAddress inetAddress=InetAddress.getByName("localhost");
			socket=new Socket(inetAddress,8999);
			//��ȡ�����
			os=socket.getOutputStream();
			int len;
			byte []buffer=new byte[10];
			while((len=bis.read(buffer))!=-1) {
				os.write(buffer, 0, len);
			}
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {  //�ر���Դ
			try {
				if(os!=null) {
					os.close();
				}
				if(bis!=null) {
					bis.close();
				}
				if(socket!=null) {
					socket.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}