package thread;
/**
 * 
 * @Description: Thread的方法测试
 * @author  karlieswift
 * @date 2020年4月10日
 * @version "13.0.1"
 * 
 * Method方法介绍
 * sleep(long Millionstime) this Thread will sleep time
 * yeild():this Thread will take off cpu to other thread
 * join():在线程a中调用线程b的join(),此时线程a就进入阻塞状态，直到线程b完全执行完以后，线程a才
 *           结束阻塞状态。
 */
public class ThreadMethodTest {

	public static void main(String[] args) {
	
		HelloThread helloThread=new HelloThread();
		helloThread.start();
		
//		HelloThread1 helloThread1=new HelloThread1();
//		//helloThread1.setName("thread1");
//		helloThread1.start();
		for(int i=0;i<100;i++) {
				if(i%2==0) {
					System.out.println(Thread.currentThread().getName()+" "+i);	
				}
				if(i==20) {
					try {
						helloThread.join();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		System.out.println(helloThread.isAlive());
	}
}
class HelloThread extends Thread{
	@Override
	public void run() {
		for(int i=0;i<100;i++) {
			if(i%2==0) {
				//休眠
				try {
					sleep(40);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+" "+i);	
			}
//			if(i%20==0) {
//				yield();
//			}
			
		}
	}
	
}


class HelloThread1 extends Thread{
	@Override
	public void run() {
		for(int i=0;i<100;i++) {
//			if(i%2==0) {
//				//休眠
//				try {
//					sleep(20);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				System.out.println(Thread.currentThread().getName()+" "+i);	
			}
			
		}
	}
	













