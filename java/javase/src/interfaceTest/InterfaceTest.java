package interfaceTest;

public class InterfaceTest {

	public static void main(String[] args) {
		//1-������������
		Computer com=new Computer();
		com.show(new Flash());
		com.show(new Printer());
		//2-�����˽ӿڵ�����ʵ����ķ���������
		Usb phone=new Usb() {
			
			public void start() {
				// TODO Auto-generated method stub
				System.out.println("phone��ʼ");
			}

			@Override
			public void stop() {
				// TODO Auto-generated method stub
				System.out.println("phone����");
			}
		};
		
		com.show(phone);
		
		//3-�����˽ӿڵ�����ʵ�������������
		com.show(new Flash() {
			public void start() {
				// TODO Auto-generated method stub
				System.out.println("ipad��ʼ");
			}

			@Override
			public void stop() {
				// TODO Auto-generated method stub
				System.out.println("ipad����");
			}
		});
	}
}
class Computer {
	public void show(Usb usb) {
		usb.start();
		usb.stop();
	}
}
interface Usb {
	public static final int MIN = 1;
	public static final int MAX = 2;

	public abstract void start();
	void stop();
}
class Flash implements Usb{

	@Override
	public void start() {
		// TODO Auto-generated method stub
		System.out.println("Flash��ʼ");
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		System.out.println("Flash����");
	}
	
}


class Printer implements Usb{

	@Override
	public void start() {
		// TODO Auto-generated method stub
		System.out.println("Printer��ʼ");
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		System.out.println("Printer����");
	}
	
}