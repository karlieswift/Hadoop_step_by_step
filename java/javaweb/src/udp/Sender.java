package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
/**
 * DatagramScoket
 * DatagramPocket
 * @Description: <Function>
 * @author  karlieswift
 * @date 2020Äê4ÔÂ12ÈÕ
 * @version "13.0.1"
 */
public class Sender {

	public static void main(String[] args) throws IOException {
		sender();
	}
	public static void sender() throws IOException {
		
		DatagramSocket datagramSocket=new DatagramSocket();
		byte[]buffer="taylor swift and karlie kloss".getBytes();
		InetAddress inetAddress=InetAddress.getLocalHost();
		DatagramPacket datagramPacket=new DatagramPacket(buffer, buffer.length, inetAddress,9999);
		datagramSocket.send(datagramPacket);	
		datagramSocket.close();
	}
}
