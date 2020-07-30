package thread;

/**
 *�����̷߳���һ���̳�Thread
 *�����̷߳����� ��ʵ��Runnable�ӿ�
 * @Description: <Function>
 * @author  karlieswift
 * @date 2020��4��10��
 * @version "13.0.1"
 * 
 * yield()�ͷŵ�ǰ���̵�cpu
 */

public class Threadtest {
public static void main(String[] args) {
	Thread1 t1=new Thread1();
	Thread2 t2=new Thread2();
//	t1.run();   ����ֱ�ӵ���run(),��������ֱ�ӵ��ú����������߳�
//	t2.run();
	t1.start();
//	t1.start();  //�����ٴε�������̳߳���java.lang.IllegalThreadStateException
	t2.start();
//	t1.setPriority(10);
//	t2.setPriority(1);
//	System.out.println(t1.getPriority());
//	System.out.println(t2.getPriority());
//	System.out.println("taylor");
	 for (int i = 0; i < 100; i++) {
         if(i % 2 == 0){
             System.out.println(Thread.currentThread().getName() + ":" + i);
         }
     }
}
	
}
class Thread1 extends Thread{
	
	@Override
	public void run() {
		for(int i=0;i<100;i++) {
			if(i%2==0) {
				System.out.println(this.currentThread().getName()+"->"+i);
			}
		}
	}
}

class Thread2 extends Thread{
	@Override
	public void run() {
		for(int i=0;i<100;i++) {
			if(i%2==1) {
				System.out.println(this.currentThread().getName()+"->"+i);
			}
		}
	}
}