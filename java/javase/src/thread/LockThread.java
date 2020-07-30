package thread;

//��������ʾ
class A {
	public synchronized void foo(B b) { // ͬ����������A��Ķ���a
		System.out.println("��ǰ�߳���: " + Thread.currentThread().getName() + " ������Aʵ����foo����"); // ��
//		try {
//			Thread.sleep(200);
//		} catch (InterruptedException ex) {
//			ex.printStackTrace();
//		}
		System.out.println("��ǰ�߳���: " + Thread.currentThread().getName() + " ��ͼ����Bʵ����last����"); // ��
		b.last();
	}

	public synchronized void last() {// ͬ����������A��Ķ���a
		System.out.println("������A���last�����ڲ�");
	}
}

class B {
	public synchronized void bar(A a) {// ͬ����������b
		System.out.println("��ǰ�߳���: " + Thread.currentThread().getName() + " ������Bʵ����bar����"); // ��
//		try {
//			Thread.sleep(200);
//		} catch (InterruptedException ex) {
//			ex.printStackTrace();
//		}
		System.out.println("��ǰ�߳���: " + Thread.currentThread().getName() + " ��ͼ����Aʵ����last����"); // ��
		a.last();
	}

	public synchronized void last() {// ͬ����������b
		System.out.println("������B���last�����ڲ�");
	}
}

public class LockThread implements Runnable {
	A a = new A();
	B b = new B();

	public void init() {
		Thread.currentThread().setName("���߳�");
		// ����a�����foo����
		a.foo(b);
		System.out.println("���������߳�֮��");
	}

	public void run() {
		Thread.currentThread().setName("���߳�");
		// ����b�����bar����
		b.bar(a);
		System.out.println("�����˸��߳�֮��");
	}

	public static void main(String[] args) {
		LockThread dl = new LockThread();
		new Thread(dl).start();

		dl.init();
	}
}
