package generic;

import java.util.List;

/**
 * 泛型
 * 
 * @Description: <Function>
 * @author karlieswift
 * @date 2020年4月7日
 * @version "13.0.1"
 */
public class GenericTest1 {

	public static void main(String[] args) {
//		Person<String> p=new Person();
//		p.fun("1");
		Person s = new Student();
		s.fun(11);
		Person<String> p = new Person();
		System.out.println(p.f(1, "Str"));
//		List<Person> p1=null;
//		List<Student> s2=null;
//		p1=s2;//报错
		
	}
}

class Person<T> {
	int age;
	T x;

	public <T> T f(int x, T e) {
		return e;
	}

	public void fun(int x) {
		System.out.println("person");
		System.out.println(x);
	}
	
	
	
}

class Student extends Person<Integer> {
	public void fun(int x) {
		System.out.println("student");
		System.out.println(x);
	}

}