package com.tay.objectTest;

/**
 * @author karlieswift
 * date: 2020/6/29 10:56
 * ClassName: Test
 * @version java "13.0.1"
 */
public class PersonDemo {
    public static void main(String[] args) throws CloneNotSupportedException {



        Person p=new Person();
        p.age=23;
        p.name="taylor";
        System.out.println(p.hashCode()+":"+p.toString());//1922154895:Person{name='taylor', age=23}

        Person p1=p;
        System.out.println(p1.hashCode()+":"+p1.toString());//1922154895:Person{name='taylor', age=23}
        Person p2= (Person)p.clone();
        System.out.println(p2.hashCode()+":"+p2.toString());//883049899:Person{name='taylor', age=23}

    }


}

class Person implements Cloneable{

    String name;
    int age;
    protected int anInt;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}