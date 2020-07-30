package io_text;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * ������                                      �ڵ���                                        ������
 * InputStream             FileInputStream        BufferedInputStream
 * OutputStream            FileOutputStream        BufferedOutputStream         
 * Writer                   FileWiter             BufferedWriter
 * Reader                   FileReader            BufferedReader
 * ������
 * Ҫ��һ����������ϵ�л�����������һ��Ҫ�ͣ�
 * ����ʵ��implements �ӿ�Serialazable�����Ҵ������а汾��ȫ�ֳ���serialVersion
 * @Description: <Function>
 * @author  karlieswift
 * @date 2020��4��7��
 * @version "13.0.1"
 */
public class ObjectInputOutputStreamTest {
public static void main(String[] args) throws IOException {
	//���л����̣����ڴ��е�java���󱣴浽�����л���ͨ�����紫���ȥ
	//ʹ��ObjectOutPutStreamʵ��
	ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("test//object.txt"));
	oos.writeObject(new String("taylor"));
	oos.writeObject(new Person(23,"taylor"));
	oos.flush();
	oos.close();
	//�����л����� �����̵Ķ���װ��λjava�Ķ���
	//ObjectInputStream
	ObjectInputStream ois=new ObjectInputStream(new FileInputStream("test//object.txt"));
	Object obj = null;
	try {
		//��ȡstring ����
		obj = ois.readObject();
		String str=(String)obj;
		//��ȡperson����
		Person p=(Person)ois.readObject();
		System.out.println(str);//taylor
		System.out.println(p);//Person [age=23, name=taylor]
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	ois.close();
	
	
}
}



class Person implements Serializable{
	//����ʵ��implements �ӿ�Serialazable�����Ҵ������а汾��ȫ�ֳ���serialVersion
	public static final long serialVersion=984247924288L;
	int age;
	String name;
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Person(int age, String name) {
		super();
		this.age = age;
		this.name = name;
	}
	@Override
	public String toString() {
		return "Person [age=" + age + ", name=" + name + "]";
	}
	
}
