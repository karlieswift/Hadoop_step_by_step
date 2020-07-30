package io_text;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * 抽象类                                      节点流                                        缓冲流
 * InputStream             FileInputStream        BufferedInputStream
 * OutputStream            FileOutputStream        BufferedOutputStream         
 * Writer                   FileWiter             BufferedWriter
 * Reader                   FileReader            BufferedReader
 * 对象流
 * 要想一个对象满足系列化，必须满足一下要就：
 * 必须实现implements 接口Serialazable，并且传入序列版本号全局常量serialVersion
 * @Description: <Function>
 * @author  karlieswift
 * @date 2020年4月7日
 * @version "13.0.1"
 */
public class ObjectInputOutputStreamTest {
public static void main(String[] args) throws IOException {
	//序列化过程：将内存中的java对象保存到磁盘中或者通过网络传输出去
	//使用ObjectOutPutStream实现
	ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("test//object.txt"));
	oos.writeObject(new String("taylor"));
	oos.writeObject(new Person(23,"taylor"));
	oos.flush();
	oos.close();
	//反序列化过程 将磁盘的对象装换位java的对象
	//ObjectInputStream
	ObjectInputStream ois=new ObjectInputStream(new FileInputStream("test//object.txt"));
	Object obj = null;
	try {
		//读取string 对象
		obj = ois.readObject();
		String str=(String)obj;
		//读取person对象
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
	//必须实现implements 接口Serialazable，并且传入序列版本号全局常量serialVersion
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
