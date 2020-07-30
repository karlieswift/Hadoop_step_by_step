package interfaceTest;

public class InterfaceTest {

	public static void main(String[] args) {
		//1-创建匿名对象
		Computer com=new Computer();
		com.show(new Flash());
		com.show(new Printer());
		//2-创建了接口的匿名实现类的非匿名对象
		Usb phone=new Usb() {
			
			public void start() {
				// TODO Auto-generated method stub
				System.out.println("phone开始");
			}

			@Override
			public void stop() {
				// TODO Auto-generated method stub
				System.out.println("phone结束");
			}
		};
		
		com.show(phone);
		
		//3-创建了接口的匿名实现类的匿名对象
		com.show(new Flash() {
			public void start() {
				// TODO Auto-generated method stub
				System.out.println("ipad开始");
			}

			@Override
			public void stop() {
				// TODO Auto-generated method stub
				System.out.println("ipad结束");
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
		System.out.println("Flash开始");
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		System.out.println("Flash结束");
	}
	
}


class Printer implements Usb{

	@Override
	public void start() {
		// TODO Auto-generated method stub
		System.out.println("Printer开始");
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		System.out.println("Printer结束");
	}
	
}