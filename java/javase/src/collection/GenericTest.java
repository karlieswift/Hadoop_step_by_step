package collection;

import java.util.HashSet;
import java.util.Set;

public class GenericTest {
	public static void main(String[] args) {

		Set<String> set = new HashSet<String>();
		// System.out.println(set.add(125));//����

		set.add(new String("public"));
		set.add("class");
		set.add("abcdf");
		set.add(new String("abcdfghi"));

		System.out.println("Set�����е�Ԫ�ظ�����" + set.size());
		System.out.println(set);

		for (String s : set) {
			System.out.println("Set�����е�Ԫ�أ�" + s);
			System.out.println(s.toUpperCase());
		}
	}
}
