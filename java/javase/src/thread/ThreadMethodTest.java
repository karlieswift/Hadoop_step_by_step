package thread;
/**
 * 
 * @Description: Thread�ķ�������
 * @author  karlieswift
 * @date 2020��4��10��
 * @version "13.0.1"
 * 
 * Method��������
 * sleep(long Millionstime) this Thread will sleep time
 * yeild():this Thread will take off cpu to other thread
 * join():���߳�a�е����߳�b��join(),��ʱ�߳�a�ͽ�������״̬��ֱ���߳�b��ȫִ�����Ժ��߳�a��
 *           ��������״̬��
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
				//����
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
//				//����
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
	













