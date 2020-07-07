package com.tay.other;

/**
 * @author karlieswift
 * date: 2020/7/3 18:06
 * ClassName: ThreadTest2
 * @version java "13.0.1"
 */
public class ThreadTest2 {

    public static void main(String[] args) {

        Person person = new Person();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Person person1 = new Person();
                person1.name="taylor";
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(person1.name);
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                Person person2 = new Person();
                person2.name="karlie";
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(person2.name);
            }
        });


        thread1.start();
        thread2.start();
        System.out.println(Thread.currentThread().getName()+"执行完毕");

    }
}

class Person{
String name;
}