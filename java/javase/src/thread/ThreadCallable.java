package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * �����̵߳ķ�ʽ����ʵ��Callable�ӿڡ� --- JDK 5.0����
 *
 *
 * ������ʵ��Callable�ӿڵķ�ʽ�������̱߳�ʵ��Runnable�ӿڴ������̷߳�ʽǿ��
 * 1. call()�����з���ֵ�ġ�
 * 2. call()�����׳��쳣��������Ĳ������񣬻�ȡ�쳣����Ϣ
 * 3. Callable��֧�ַ��͵�

 * @Description: <Function>
 * @author  karlieswift
 * @date 2020��4��11��
 * @version "13.0.1"
 */
//1.����һ��ʵ��Callable��ʵ����
class NumThread implements Callable{
    //2.ʵ��call�����������߳���Ҫִ�еĲ���������call()��
    @Override
    public Object call() throws Exception {
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            if(i % 2 == 0){
                System.out.println(i);
                sum += i;
            }
        }
        return sum;
    }
}


public class ThreadCallable {
    public static void main(String[] args) {
        //3.����Callable�ӿ�ʵ����Ķ���
        NumThread numThread = new NumThread();
        //4.����Callable�ӿ�ʵ����Ķ�����Ϊ���ݵ�FutureTask�������У�����FutureTask�Ķ���
        FutureTask futureTask = new FutureTask(numThread);
        //5.��FutureTask�Ķ�����Ϊ�������ݵ�Thread��Ĺ������У�����Thread���󣬲�����start()
        new Thread(futureTask).start();

        try {
            //6.��ȡCallable��call�����ķ���ֵ
            //get()����ֵ��ΪFutureTask����������Callableʵ������д��call()�ķ���ֵ��
            Object sum = futureTask.get();
            System.out.println("�ܺ�Ϊ��" + sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
