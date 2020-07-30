package collection;



import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
/**
 * TreeSet集合练习————————自然排序
 * @Description: <Function>
 * @author  karlieswift
 * @date 2020年4月7日
 * @version "13.0.1"
 */
public class TreeSetTest2 {

	public static void main(String[] args) {
	//自然排序
		TreeSet<Account2> tree=new TreeSet<>();
 
		
		tree.add(new Account2("taylor", 12));
		tree.add(new Account2("taylor1", 132));
		tree.add(new Account2("taylor", 12));
		tree.add(new Account2("taylor", 13));
		tree.add(new Account2("taylor", 33));
		tree.add(new Account2("taylor", 93));
		showset(tree);
		
	}
	public static void showset(Set set) {
		Iterator it=set.iterator();
		while(it.hasNext()) {
			System.out.println(it.next()+" ");
		}
 
	}
}
 
class Account2 implements Comparable<Account2>  {
	String name;
	int age;
	public Account2(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	@Override
	public String toString() {
		return "Account [name=" + name + ", age=" + age + "]";
	}

	@Override
	public int compareTo(Account2 o) {
		int cmp=this.name.compareTo(o.name);
		if(cmp!=0) {
			return cmp;
		}else {
			return this.age-o.age;
	}

	}
}
	