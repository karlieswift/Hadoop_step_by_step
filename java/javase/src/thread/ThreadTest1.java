package thread;
/**
 * 创建线程方法一：继承Thread
 *创建线程方法二 ：实现Runnable接口
 * @Description: <Function>
 * @author  karlieswift
 * @date 2020年4月10日
 * @version "13.0.1"
 */
public class ThreadTest1 {

	public static void main(String[] args) {
		//创建一个线程的实例对象
		Thread21 t21=new Thread21();
		//将线程实例作为参数传入Thread掺入
		Thread thread1=new Thread(t21);
		thread1.start();
	}
}
class Thread21 implements Runnable{

	@Override
	public void run() {
		for(int i=0;i<100;i++) {
			if(i%2==1) {
				System.out.println(Thread.currentThread().getName()+"->" +i);
			}
		}
		
	}
	
}