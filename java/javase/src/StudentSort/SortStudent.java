package StudentSort;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
/**
 * Treeset 与hashset
 * @Description: Treeset需要进行自然排序	
 * @author  karlieswift
 * @date 2020年4月8日
 * @version "13.0.1"
 * 
 * 
 * Hashset进行数据添加时，如果不进行类进行hashcode与equal方法进行重写，虽然可以添加成功，但会添加重复的对象
 * 所以进行hashcode与equal进行重写
 * 
 */
public class SortStudent {

	
	public static void main(String[] args) {
		//Hashset需要重写hashcode与equals方法 否则将添加重复的对象
//		HashSet<Student> set=new HashSet<Student>();
//		set.add(new Student("taylor swift1", 19, new MyDate(1996, 12, 12)));
//		set.add(new Student("taylor swift1", 19, new MyDate(19926, 12, 12)));
//		set.add(new Student("taylor swift3", 19, new MyDate(1996, 12, 12)));
//		set.add(new Student("taylor swift4", 19, new MyDate(1996, 12, 12)));
//		Iterator it =set.iterator();
//		while(it.hasNext()) {
//			System.out.println(it.next().toString());
//		}
//		
	//Treeset需要进行自然排序	
		//自然排序comparable  compareTo的重写	
		TreeSet<Student> tree=new TreeSet<Student>();	
		tree.add(new Student("taylor swift1", 19, new MyDate(1996, 12, 12)));
		tree.add(new Student("taylor swift1", 19, new MyDate(1996, 9, 12)));
		tree.add(new Student("taylor swift6", 19, new MyDate(1996, 9, 1)));
		tree.add(new Student("taylor swift3", 19, new MyDate(1996, 9, 12)));
		tree.add(new Student("taylor swift1", 19, new MyDate(1996, 9, 1)));
		tree.add(new Student("taylor swift4", 19, new MyDate(1996, 12, 12)));
		Iterator it =tree.iterator();
		while(it.hasNext()) {
			System.out.println(it.next().toString());
		}
		
		
		
	}
}
