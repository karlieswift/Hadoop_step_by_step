package thread;
/**
 * �����̷߳���һ���̳�Thread
 *�����̷߳����� ��ʵ��Runnable�ӿ�
 * @Description: <Function>
 * @author  karlieswift
 * @date 2020��4��10��
 * @version "13.0.1"
 */
public class ThreadTest1 {

	public static void main(String[] args) {
		//����һ���̵߳�ʵ������
		Thread21 t21=new Thread21();
		//���߳�ʵ����Ϊ��������Thread����
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