package thread;

/**
 * 
 * @Description: <Function>
 * @author karlieswift
 * @date 2020年4月10日
 * @version "13.0.1" 使用同步代码块解决继承Thread类的方式的线程安全问题
 *          例子：创建三个窗口卖票，总票数为100张.使用继承Thread类的方式
 *          说明：在继承Thread类创建多线程的方式中，慎用this充当同步监视器，考虑使用当前类充当同步监视器。
 *          synchronized:当使用Runnable()接口实现时synchronized(this)//因为制定一个windows
 *          synchronized:当使用继承Thread()实现时synchronized(this)/是错误的，因为THread这种方法创建额多个进程
 *          this代表着t1,t2,t3三个对象,此时其中一个进程将一直被锁知道进程结束
 *          应该写为synchronized(Window.class)
 * 
 * 
 * 
 */
public class TicketsThreadTest {

	public static void main(String[] args) {
		// 方法一
		Window t1 = new Window();
		Window t2 = new Window();
		Window t3 = new Window();
		t1.setName("窗口1");
		t2.setName("窗口2");
		t3.setName("窗口3");
		t1.start();
		t2.start();
		t3.start();

//		//方法二
//        Window1 w = new Window1();
//        Thread t1 = new Thread(w);
//        Thread t2 = new Thread(w);
//        Thread t3 = new Thread(w);
//        t1.setName("窗口1");
//        t2.setName("窗口2");
//        t3.setName("窗口3");
//        t1.start();
//        t2.start();
//        t3.start();
//        

//      //方法三
//      		  Window2 t1 = new Window2();
//              Window2 t2 = new Window2();
//              Window2 t3 = new Window2();
//              t1.setName("窗口1");
//              t2.setName("窗口2");
//              t3.setName("窗口3");
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
					System.out.println(Thread.currentThread().getName() + "票号：" + tickets);
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
					System.out.println(Thread.currentThread().getName() + "票号：" + tickets);
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
			System.out.println(Thread.currentThread().getName() + "票号：" + tickets);
			tickets--;
		}

	}
}
