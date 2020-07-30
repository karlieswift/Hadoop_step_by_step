package StudentSort;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
/**
 * Treeset ��hashset
 * @Description: Treeset��Ҫ������Ȼ����	
 * @author  karlieswift
 * @date 2020��4��8��
 * @version "13.0.1"
 * 
 * 
 * Hashset�����������ʱ����������������hashcode��equal����������д����Ȼ������ӳɹ�����������ظ��Ķ���
 * ���Խ���hashcode��equal������д
 * 
 */
public class SortStudent {

	
	public static void main(String[] args) {
		//Hashset��Ҫ��дhashcode��equals���� ��������ظ��Ķ���
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
	//Treeset��Ҫ������Ȼ����	
		//��Ȼ����comparable  compareTo����д	
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
