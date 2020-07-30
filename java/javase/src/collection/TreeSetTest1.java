package collection;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
/**
 * TreeSet集合练习————————定制排序
 * @Description: <Function>
 * @author  karlieswift
 * @date 2020年4月7日
 * @version "13.0.1"
 */
public class TreeSetTest1 {

	public static void main(String[] args) {
	//定制排序
		Comparator<Account1> com=new Comparator<Account1>() {
			@Override
			public int compare(Account1 o1, Account1 o2) {
				int cmp=o1.name.compareTo(o2.name);
				if(cmp!=0) {
					return cmp;
				}else {
					return o1.age-o2.age;
			}
			}
		};
		TreeSet<Account1> tree=new TreeSet<>(com);
 
		
		tree.add(new Account1("taylor", 12));
		tree.add(new Account1("taylor", 12));
		tree.add(new Account1("taylor1", 132));
		tree.add(new Account1("taylor", 23));
		tree.add(new Account1("taylor", 13));
		tree.add(new Account1("taylor", 33));
		tree.add(new Account1("taylor", 93));
		showset(tree);
		
	}
	public static void showset(Set set) {
		Iterator it=set.iterator();
		while(it.hasNext()) {
			System.out.println(it.next()+" ");
		}
 
	}
}
//指定构造器
class Account1  {
	String name;
	int age;
	public Account1(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	@Override
	public String toString() {
		return "Account [name=" + name + ", age=" + age + "]";
	}

 
	}
	