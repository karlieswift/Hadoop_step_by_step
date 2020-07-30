package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Reciever {
	public static void main(String[] args) throws IOException {
		reciver();
	}

	public static void reciver() throws IOException {
		
		DatagramSocket datagramSocket=new DatagramSocket(9999);
		byte[]buffer=new byte[100];
		DatagramPacket datagramPacket=new DatagramPacket(buffer, 0,buffer.length);
		datagramSocket.receive(datagramPacket);
		System.out.println(new String(datagramPacket.getData()));
		datagramSocket.close();
	}
}
