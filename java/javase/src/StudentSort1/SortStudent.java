package StudentSort1;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
/**
 * Treeset ��hashset
 * @Description: TreeSet��������
 * @author  karlieswift
 * @date 2020��4��8��
 * @version "13.0.1"
 * 
 *
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
		
		//Treeset��Ҫ���ж�������
		//��������comparator  compare����д	
		Comparator<Student> com=new Comparator<Student>() {

			@Override
			public int compare(Student o1, Student o2) {			
				int name=o1.getName().compareTo(o2.getName());
				int age=o1.getAge()-o2.getAge();
				if(name!=0) {
					return name;
				}else if(age!=0){
					return age;
				}else {
					return o1.bitrhday.compareTo(o2.bitrhday);
				}
			}
		};
		
		TreeSet<Student> tree=new TreeSet<Student>(com);	
		tree.add(new Student("taylor swift1", 19, new MyDate(1996, 12, 12)));
		tree.add(new Student("taylor swift1", 19, new MyDate(1996, 9, 12)));
		tree.add(new Student("taylor swift1", 19, new MyDate(1996, 9, 1)));
		tree.add(new Student("taylor swift4", 19, new MyDate(1996, 12, 12)));
		tree.add(new Student("taylor swift2", 19, new MyDate(1996, 9, 1)));
		tree.add(new Student("taylor swift6", 19, new MyDate(1996, 12, 12)));
		Iterator it =tree.iterator();
		while(it.hasNext()) {
			System.out.println(it.next().toString());
		}
		
		
	}
}
