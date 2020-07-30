package thread;

/**
 *创建线程方法一：继承Thread
 *创建线程方法二 ：实现Runnable接口
 * @Description: <Function>
 * @author  karlieswift
 * @date 2020年4月10日
 * @version "13.0.1"
 * 
 * yield()释放当前进程的cpu
 */

public class Threadtest {
public static void main(String[] args) {
	Thread1 t1=new Thread1();
	Thread2 t2=new Thread2();
//	t1.run();   不能直接调用run(),这样就是直接调用函数，不是线程
//	t2.run();
	t1.start();
//	t1.start();  //不能再次调用这个线程出现java.lang.IllegalThreadStateException
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