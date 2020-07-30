package collection;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
/**
 * Collection ��Set�ӿ�:
 * HashSet:��Set�ӿڵĵ���ʵ�֣�����ϣֵ���д�ȡ���󼯺�
 * HashSet���������ܱ�֤���ݵ�˳��洢���̲߳���ȫ�����ϵ�Ԫ�ؿ���Ϊnull,listҲ����Ϊ��
 * ʵ��ԭ����hashSet����Ҫ��Ӷ����ǣ�����ͨ��hashcode����ö���ĵĹ�ϣֵ��Ȼ����ݹ�ϣֵ
 * �жϸö���Ĵ洢λ��
 * ��ҲҪ�ж����������Ƿ���ͬ��׼:��ͨ�������hashcode()�����Ƚ���ȣ��������������equals
 * ��������ֵҲ��ͬ
 * ���������String������Ҫ������дhashcode��equals,�Լ���Ķ�����Ҫ��д
 * 
 * 
 * 
 * 
 * 
 * TreeSet������Ķ��߼��ϣ������
 * LinkedHashSet:���չ�ϣ�㷨����ȡ�����еĶ��󣬱�����ʱ�򣬻ᰴ���������
 * �����˳����б���
 * 
 * 
 * @Description: <Function>
 * @author  karlieswift
 * @date 2020��4��7��
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
