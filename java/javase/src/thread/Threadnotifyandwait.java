package thread;

public class Threadnotifyandwait {

	public static void main(String[] args) {
		Thread11 t = new Thread11();
		Thread t1 = new Thread(t);
		Thread t2 = new Thread(t);
		t1.start();
		t2.start();

	}
}

class Thread11 implements Runnable {

	private int n = 1;
	Object obj = new Object();

	@Override
	public void run() {

		while (true) {
			synchronized (obj) {
				obj.notify();

				if (n <= 100) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + ":" + n++);
					try {
						obj.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					break;
				}

			}
		}

	}

}