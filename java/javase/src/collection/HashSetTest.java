package collection;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
/**
 * Collection 的Set接口:
 * HashSet:是Set接口的典型实现，按哈希值进行存取对象集合
 * HashSet特征：不能保证数据的顺序存储，线程不安全，集合的元素可以为null,list也可以为空
 * 实现原理：当hashSet集合要添加对象是，首先通过hashcode计算该对象的的哈希值，然后根据哈希值
 * 判断该对象的存储位置
 * 他也要判断两个对象是否相同标准:，通过对象的hashcode()方法比较相等，并且两个对象的equals
 * 方法返回值也相同
 * 当加入的是String对象不需要进行重写hashcode与equals,自己造的对象需要重写
 * 
 * 
 * 
 * 
 * 
 * TreeSet：有序的对线集合，红黑树
 * LinkedHashSet:按照哈希算法来存取集合中的对象，便利的时候，会按照添加数据
 * 对象的顺序进行遍历
 * 
 * 
 * @Description: <Function>
 * @author  karlieswift
 * @date 2020年4月7日
 * @version "13.0.1"
 */
public class HashSetTest {
	public static void main(String[] args) {

		
		Set set=new HashSet ();
//		set.add("taylor");
//		set.add("taylor");
//		set.add(1);
//		showset(set);
		set.add(new Student("taylor", 12));
		set.add(new Student("taylor", 12));
		set.add(new Student("taylor", 1201));
		set.add(new Student("taylor", 1202));
		set.add(new Student("taylor", 1220));
		set.add(new Student("taylor", 1020));
		showset(set);
		
	}
	public static void showset(Set set) {
		Iterator it=set.iterator();
		while(it.hasNext()) {
			System.out.print(it.next()+" ");
			System.out.println();
		}
	}
}

class Student {
 
String name;
int age;
public Student(String name, int age) {
	super();
	this.name = name;
	this.age = age;
}
@Override
public String toString() {
	return "Student [name=" + name + ", age=" + age + "]";
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + age;
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Student other = (Student) obj;
	if (age != other.age)
		return false;
	if (name == null) {
		if (other.name != null)
			return false;
	} else if (!name.equals(other.name))
		return false;
	return true;
}

}
