package thread;

/**
 * 
 * @Description: <Function>
 * @author karlieswift
 * @date 2020��4��10��
 * @version "13.0.1" ʹ��ͬ����������̳�Thread��ķ�ʽ���̰߳�ȫ����
 *          ���ӣ���������������Ʊ����Ʊ��Ϊ100��.ʹ�ü̳�Thread��ķ�ʽ
 *          ˵�����ڼ̳�Thread�ഴ�����̵߳ķ�ʽ�У�����this�䵱ͬ��������������ʹ�õ�ǰ��䵱ͬ����������
 *          synchronized:��ʹ��Runnable()�ӿ�ʵ��ʱsynchronized(this)//��Ϊ�ƶ�һ��windows
 *          synchronized:��ʹ�ü̳�Thread()ʵ��ʱsynchronized(this)/�Ǵ���ģ���ΪTHread���ַ���������������
 *          this������t1,t2,t3��������,��ʱ����һ�����̽�һֱ����֪�����̽���
 *          Ӧ��дΪsynchronized(Window.class)
 * 
 * 
 * 
 */
public class TicketsThreadTest {

	public static void main(String[] args) {
		// ����һ
		Window t1 = new Window();
		Window t2 = new Window();
		Window t3 = new Window();
		t1.setName("����1");
		t2.setName("����2");
		t3.setName("����3");
		t1.start();
		t2.start();
		t3.start();

//		//������
//        Window1 w = new Window1();
//        Thread t1 = new Thread(w);
//        Thread t2 = new Thread(w);
//        Thread t3 = new Thread(w);
//        t1.setName("����1");
//        t2.setName("����2");
//        t3.setName("����3");
//        t1.start();
//        t2.start();
//        t3.start();
//        

//      //������
//      		  Window2 t1 = new Window2();
//              Window2 t2 = new Window2();
//              Window2 t3 = new Window2();
//              t1.setName("����1");
//              t2.setName("����2");
//              t3.setName("����3");
//              t1.start();
//              t2.start();
//              t3.start();
	}
}

class Window extends Thread {
	private static int tickets = 2;

	@Override
	public void run() {
		while (tickets > 0) {
			synchronized (Window.class) {
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (tickets > 0) {
					System.out.println(Thread.currentThread().getName() + "Ʊ�ţ�" + tickets);
					tickets--;
				} else {
					break;
				}
			}
		}
	}
}

class Window1 implements Runnable {
	private int tickets = 10;

	@Override
	public void run() {
		while (tickets > 0) {
			synchronized (this) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (tickets > 0) {
					System.out.println(Thread.currentThread().getName() + "Ʊ�ţ�" + tickets);
					tickets--;
				} else {
					break;
				}
			}
		}
	}
}

class Window2 extends Thread {
	private static int tickets = 10;

	@Override
	public  void run() {
		while (true) {
			show();
		}
	}

	private static synchronized void show() {

		if (tickets > 0) {
			try {
				sleep(33);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "Ʊ�ţ�" + tickets);
			tickets--;
		}

	}
}
