package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * �����̵߳ķ�ʽ�ģ�ʹ���̳߳�
 *
 * �ô���
 * 1.�����Ӧ�ٶȣ������˴������̵߳�ʱ�䣩
 * 2.������Դ���ģ��ظ������̳߳����̣߳�����Ҫÿ�ζ�������
 * 3.�����̹߳���
 *      corePoolSize�����ĳصĴ�С
 *      maximumPoolSize������߳���
 *      keepAliveTime���߳�û������ʱ��ౣ�ֶ೤ʱ������ֹ
 * @Description: <Function>
 * @author  karlieswift
 * @date 2020��4��11��
 * @version "13.0.1"
 */

class NumberThread implements Runnable{

    @Override
    public void run() {
        for(int i = 0;i <= 100;i++){
            if(i % 2 == 0){
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }
}

class NumberThread1 implements Runnable{

    @Override
    public void run() {
    	 
        for(int i = 0;i <= 100;i++){
            if(i % 2 != 0){
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }
}

public class ThreadPool {

    public static void main(String[] args) {
        //1. �ṩָ���߳��������̳߳�
        ExecutorService service = Executors.newFixedThreadPool(10);
        ThreadPoolExecutor service1 = (ThreadPoolExecutor) service;
        //�����̳߳ص�����
//        System.out.println(service.getClass());
//        service1.setCorePoolSize(15);
//        service1.setKeepAliveTime();
        //2.ִ��ָ�����̵߳Ĳ�������Ҫ�ṩʵ��Runnable�ӿڻ�Callable�ӿ�ʵ����Ķ���
        service.execute(new NumberThread());//�ʺ�������Runnable
        service.execute(new NumberThread1());//�ʺ�������Runnable
//        service.submit(Callable callable);//�ʺ�ʹ����Callable
        //3.�ر����ӳ�
        service.shutdown();
    }

}
